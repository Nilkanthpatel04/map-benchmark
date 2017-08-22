import org.junit.Test;

public class IgniteMapBenchmarkTest {
  private MyIgniteMap<String, String> igniteMap = new MyIgniteMap<>();

  MapBenchmark benchmark = new MapBenchmark();

  @Test
  public void putBenchmarkTest() {
    benchmark.runPutBenchmark(igniteMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }

  @Test
  public void getBenchmarkTest() {
    benchmark.runGetBenchmark(igniteMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }

  @Test
  public void putGetBenchmarkTest() {
    benchmark.runPutGetBenchmark(igniteMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }
}
