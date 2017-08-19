import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ConcurrentHashMapBenchmarkTest {
  private MyConcurrentHashMap<String, String> concurrentHashMap = new MyConcurrentHashMap<String, String>();

  MapBenchmark benchmark = new MapBenchmark();

  @Test
  public void putBenchmarkTest() {
    benchmark.runPutBenchmark(concurrentHashMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }

  @Test
  public void getBenchmarkTest() {
    benchmark.runGetBenchmark(concurrentHashMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }

  @Test
  public void putGetBenchmarkTest() {
    benchmark.runPutGetBenchmark(concurrentHashMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }
}
