import org.junit.Test;

public class GeodeMapBenchmarkTest {
  private MyGeodeMap<String, String> geodeMap = new MyGeodeMap<>();

  MapBenchmark benchmark = new MapBenchmark();

  @Test
  public void putBenchmarkTest() {
    benchmark.runPutBenchmark(geodeMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }

  @Test
  public void getBenchmarkTest() {
    benchmark.runGetBenchmark(geodeMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }

  @Test
  public void putGetBenchmarkTest() {
    benchmark.runPutGetBenchmark(geodeMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }
}