public interface IMap<K, V> {
  V get(K key);

  void put(K key, V val);
}
