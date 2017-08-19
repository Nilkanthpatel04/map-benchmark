import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
/**
 * Wrapper class for Hazelcast Map
 * @param <K>
 * @param <V>
 */
public class MyHazelcastMap<K, V> implements IMap<K,V> {

  HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(new Config());
  Map<K, V> hazelcastMap= hazelcastInstance.getMap("hazelcast-map");

  public V get(K key){
    return hazelcastMap.get(key);
  }

  public void put(K key, V val) {
    hazelcastMap.put(key, val);
  }

  //for the cleanup
  public HazelcastInstance getHazelcastInstance() {
    return hazelcastInstance;
  }
}