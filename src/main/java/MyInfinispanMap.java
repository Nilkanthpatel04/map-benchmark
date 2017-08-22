import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;

public class MyInfinispanMap<K,V> implements IMap<K,V> {

  // Construct a simple local cache manager with default configuration
  DefaultCacheManager cacheManager = new DefaultCacheManager();

  // Obtain the local cache
  Cache<K, V> infinispanMap = null;

  public MyInfinispanMap() {
    cacheManager.defineConfiguration("local", new ConfigurationBuilder().build());
    this.infinispanMap = cacheManager.getCache("local");
  }

  @Override
  public V get(K key) {
    return infinispanMap.get(key);
  }

  @Override
  public void put(K key, V val) {
    infinispanMap.put(key, val);
  }
}
