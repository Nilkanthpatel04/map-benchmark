/**
 * Defines a configuration used in map-benchmark.
 */
public class BenchmarkConfig {
  public static long WARM_UP_ENTRIES = 500000;

  public static long WARM_UP_KEY_MAX_INDEX = 600000;

  public static String KEY_PREFIX = "key-";

  public static String VALUE_PREFIX = "val-";

  //thread-pool size
  public static int MAX_THREADS = 6;

  public static int MAX_TASKS = 12;

  public static long TOTAL_OPS = 2500000;

  public static int DEFAULT_ITERATIONS = 10;
}
