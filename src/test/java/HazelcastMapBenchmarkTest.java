import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.junit.Test;

import java.util.Map;

public class HazelcastMapBenchmarkTest {
  private MyHazelcastMap<String, String> hazelcastMap = new MyHazelcastMap<>();

  MapBenchmark benchmark = new MapBenchmark();

  @Test
  public void putBenchmarkTest() {
    benchmark.runPutBenchmark(hazelcastMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }

  @Test
  public void getBenchmarkTest() {
    benchmark.runGetBenchmark(hazelcastMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }

  @Test
  public void putGetBenchmarkTest() {
    benchmark.runPutGetBenchmark(hazelcastMap, BenchmarkConfig.MAX_THREADS, BenchmarkConfig.MAX_TASKS, BenchmarkConfig.DEFAULT_ITERATIONS, true);
  }
}
