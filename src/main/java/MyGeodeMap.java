import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;

import java.util.Map;
/**
 * Wrapper class for Apache Geode Region map
 * @param <K>
 * @param <V>
 */
public class MyGeodeMap<K, V> implements IMap<K, V> {

  Cache geodeCache = new CacheFactory().create();
  Map<K,V> geodeMap = (Map<K, V>) geodeCache.createRegionFactory().create("geode-map");

  public V get(K key) {
    return geodeMap.get(key);
  }

  public void put(K key, V val) {
    geodeMap.put(key, val);
  }

  //Added for cleanup, at the end of every test close the cache.
  public Cache getGeodeCache() {
    return geodeCache;
  }
}

