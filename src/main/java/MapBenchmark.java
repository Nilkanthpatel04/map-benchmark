import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MapBenchmark {
  private Map map = null;

  /**
   * Put task (thread) doing puts on map
   * and return ops for the thread.
   */
  private static class PutWorker implements Callable {
    private MapBenchmark mb;

    public PutWorker(MapBenchmark mb) {
      this.mb = mb;
    }

    public Object call() throws Exception {
      return mb.benchmarkPut();
    }
  }

  /**
   * Get task (thread) doing gets on map
   * and return ops for the thread.
   */
  private static class GetWorker implements Callable {
    private MapBenchmark mb;

    public GetWorker(MapBenchmark mb) {
      this.mb = mb;
    }

    public Object call() throws Exception {
      return mb.benchmarkGet();
    }
  }

  public MapBenchmark(){
    this.map = new ConcurrentHashMap();
  }

  public long benchmarkPut(){
    long totalTime = 0L;

    for(long i=0; i< BenchmarkConfig.TOTAL_OPS; i++){
      long randomSuffix = (long) Math.ceil(Math.random() * BenchmarkConfig.TOTAL_OPS );
      long startTime = System.nanoTime();
      map.put(BenchmarkConfig.KEY_PREFIX + randomSuffix, BenchmarkConfig.VALUE_PREFIX + randomSuffix);
      long endTime = System.nanoTime();
      totalTime += (endTime - startTime);
    }
    long totalTimeInMS = totalTime/1000000L;

    //calculate ops/second.
    double ops = (1000 * BenchmarkConfig.TOTAL_OPS)/totalTimeInMS;
    //System.out.println("PUT ElapsedTime =" + totalTimeInMS + " OPS doubleVal = " + ops + " ABS Val="+ Math.abs(ops));
    return (long) Math.abs(ops);
  }

  public long benchmarkGet(){
    long totalTime = 0L;

    for(long i=0; i< BenchmarkConfig.TOTAL_OPS; i++){
      long randomSuffix = (long) Math.ceil(Math.random() * BenchmarkConfig.TOTAL_OPS );
      long startTime = System.nanoTime();
      map.get(BenchmarkConfig.KEY_PREFIX + randomSuffix);
      long endTime = System.nanoTime();
      totalTime += (endTime - startTime);
    }
    long totalTimeInMS = totalTime/1000000L;

    double ops = (1000 * BenchmarkConfig.TOTAL_OPS)/totalTimeInMS;
    //System.out.println("GET ElapsedTime = "+ totalTimeInMS+ " OPS doubleVal = " + ops + " ABS Val="+ Math.abs(ops));
    return (long) Math.abs(ops);
  }

  /**
   * warm up task.
   * @param map
   */
  public void warmUp(final IMap<String, String> map){
    for(int i =0; i< BenchmarkConfig.WARM_UP_ENTRIES; i++){
      long randomSuffix = (long) Math.ceil(Math.random() * BenchmarkConfig.WARM_UP_KEY_MAX_INDEX );
      map.put(BenchmarkConfig.KEY_PREFIX + String.valueOf(randomSuffix), BenchmarkConfig.VALUE_PREFIX + String.valueOf(randomSuffix));
      map.get(BenchmarkConfig.KEY_PREFIX + String.valueOf(randomSuffix));
    }
  }

  public long _runPutBenchmark(final IMap<String, String> map, int maxThreads, int maxTasks, boolean warmUp) throws ExecutionException, InterruptedException {
    if(warmUp){
      warmUp(map);
    }

    MapBenchmark mb = new MapBenchmark();
    List<Future<Long>> putList = new ArrayList<Future<Long>>();
    ExecutorService es = Executors.newFixedThreadPool(maxThreads);
    for(int i =0; i< maxTasks; i++){
      Future<Long> result = es.submit(new PutWorker(mb));
      putList.add(result);
    }

    long putOpThroughput = 0;
    for(Future<Long> f : putList){
      putOpThroughput += (Long)f.get();
    }

    //System.out.println("Total throughput for put = "+ putOpThroughput);
    es.shutdown();
    return putOpThroughput;

  }

  public long _runGetBenchmark(final IMap<String, String> map, int maxThreads, int maxTasks, boolean warmUp) throws ExecutionException, InterruptedException {
    if(warmUp){
      warmUp(map);
    }

    MapBenchmark mb = new MapBenchmark();
    List<Future<Long>> getList = new ArrayList<Future<Long>>();
    ExecutorService es = Executors.newFixedThreadPool(maxThreads);
    for(int i =0; i< maxTasks; i++){
      Future<Long> future = es.submit(new GetWorker(mb));
      getList.add(future);
    }

    long getOpThroughput = 0;
    for(Future<Long> f : getList){
      getOpThroughput += (Long)f.get();
    }

    //System.out.println("Get throughput = "+ getOpThroughput);
    es.shutdown();
    return getOpThroughput;
  }

  public long _runPutGetBenchmark(final IMap<String, String> map, int maxThreads, int maxTasks, boolean warmUp) throws ExecutionException, InterruptedException {
    if(warmUp){
      warmUp(map);
    }

    MapBenchmark mb = new MapBenchmark();
    List<Future<Long>> getList = new ArrayList<Future<Long>>();
    List<Future<Long>> putList = new ArrayList<Future<Long>>();

    ExecutorService es = Executors.newFixedThreadPool(maxThreads);
    for(int i =0; i< maxTasks; i++){
      if((i&1) == 0){
          putList.add(es.submit(new PutWorker(mb)));
      }else{
        getList.add(es.submit(new GetWorker(mb)));
      }
    }

    long opThroughput = 0;
    for(Future<Long> f : getList){
      opThroughput += (Long)f.get();
    }

    //System.out.println("Get throughput = "+ opThroughput);

    for(Future<Long> f : putList){
      opThroughput += (Long)f.get();
    }

    //System.out.println("Total throughput (get+put) = "+ opThroughput);
    es.shutdown();
    return opThroughput;
  }

  /**
   * Print summary by consolidating results for specified # of iterations
   * Provides min, max and avg.
   * @param numberOfTimes
   * @param label
   * @param throughput
   */
  public void printOPSSummary(int numberOfTimes, String label, long[] throughput) {
    double min = Long.MAX_VALUE;
    double max = Long.MIN_VALUE;
    double sum = 0;
    for (int i = 0; i < numberOfTimes; i++) {
      if (throughput[i] > max) {
        max = throughput[i];
      }
      if (throughput[i] < min) {
        min = throughput[i];
      }
      sum += throughput[i];
    }
    if (sum > 0) {
      System.out.printf("# Throughput %7s : min= %6s, max= %6s, avg= %6.03f --> %s\n",
          label, min, max, (sum/numberOfTimes), Arrays.toString(throughput));
    }
  }

  public void runPutBenchmark(final IMap<String, String> map, int maxThreads, int maxTasks, int nIterations, boolean warmUp) {
    try {
      long tpArray[] = new long[nIterations];
      for(int itr=0; itr< nIterations; itr++) {
        long ops = _runPutBenchmark(map, maxThreads, maxTasks, true);
        tpArray[itr] = ops/maxTasks;
        System.out.println(" itr = " + itr + " throughput =" + tpArray[itr]);
      }
      printOPSSummary(nIterations, "Put", tpArray);
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void runGetBenchmark(final IMap<String, String> map, int maxThreads, int maxTasks, int nIterations, boolean warmUp) {
    try {
      long tpArray[] = new long[nIterations];
      for(int itr=0; itr< nIterations; itr++) {
        long ops = _runGetBenchmark(map, maxThreads, maxTasks, true);
        tpArray[itr] = ops/maxTasks;
        System.out.println(" itr = " + itr + " throughput =" + tpArray[itr]);
      }
      printOPSSummary(nIterations, "GET", tpArray);
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void runPutGetBenchmark(final IMap<String, String> map, int maxThreads, int maxTasks, int nIterations, boolean warmUp) {
    try {
      long tpArray[] = new long[nIterations];
      for(int itr=0; itr< nIterations; itr++) {
        long ops = _runPutGetBenchmark(map, maxThreads, maxTasks, true);
        tpArray[itr] = ops/maxTasks;
        System.out.println(" itr = " + itr + " throughput =" + tpArray[itr]);
      }
      printOPSSummary(nIterations, "PutGet5050", tpArray);
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
