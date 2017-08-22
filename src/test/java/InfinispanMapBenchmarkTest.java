import org.junit.Test;

public class InfinispanMapBenchmarkTest {
  private MyInfinispanMap<String, String> infinispanMap = new MyInfinispanMap<>();

  MapBenchmark benchmark = new MapBenchmark();

  @Test
  public void putBenchmarkTest() {
    benchmark.runPutBenchmark(infinispanMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }

  @Test
  public void getBenchmarkTest() {
    benchmark.runGetBenchmark(infinispanMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }

  @Test
  public void putGetBenchmarkTest() {
    benchmark.runPutGetBenchmark(infinispanMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }
}
