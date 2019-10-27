public interface Map<K, V> {

    int size();

    HashMap.Node<K, V> getNode(K key);
    HashMap.Node<K, V> getNode(int index);

    V put(K key, V value);

    void remove(K key);
    void remove(HashMap.Node<K, V> node);

    boolean checkKey(K key);

    boolean isEmpty();
    void removeAll();

}
