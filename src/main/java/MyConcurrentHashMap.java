import java.util.concurrent.ConcurrentHashMap;

/**
 * Wrapper class for java ConcurrentHashMap
 * @param <K>
 * @param <V>
 */
public class MyConcurrentHashMap<K,V> implements IMap<K, V> {
  private ConcurrentHashMap<K, V> _concurrentHashMap = new ConcurrentHashMap<K, V>();

  public V get(K key) {
    return _concurrentHashMap.get(key);
  }

  public void put(K key, V val) {
    _concurrentHashMap.put(key, val);
  }
}
