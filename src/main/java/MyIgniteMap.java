import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

public class MyIgniteMap<K,V> implements IMap<K,V> {

  Ignite ignite = Ignition.start();

  // Get an instance of named cache.
  final IgniteCache<K, V> igniteMap = ignite.getOrCreateCache("cacheName");

  @Override
  public V get(K key) {
    return igniteMap.get(key);
  }

  @Override
  public void put(K key, V val) {
    igniteMap.put(key, val);
  }
}
