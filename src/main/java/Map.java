public interface Map<K, V> {

    int size();

    V get(K key);

    V put(K key, V value);

    void remove(K key);
    void remove(HashMap.Node<K, V> node);

    boolean checkKey(K key);

    boolean isEmpty();
    void removeAll();

}
