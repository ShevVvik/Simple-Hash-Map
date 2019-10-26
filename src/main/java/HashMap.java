import java.util.Map;

public class HashMap<K, V>  {

    static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node next;

        public Node(){};

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public void setNextNode(Node<K, V> node) {
            this.next = node;
        }
    }

    static int hash(Object key) {
        return (key == null) ? 0 : key.hashCode();
    }

    static int indexFor(int hash, int size) {
        return hash & (size - 1);
    }

    Node<K, V>[] array;
    int size;
    int capacity;

    public V put(K key, V value) {
        if (key == null) {
            return putForNullKey(value);
        }
        int hash = hash(key);
        int index = indexFor(hash, this.capacity);

        if (array[index] == null) {
            array[index] = new Node<>(hash, key, value);
            this.size++;
            return null;
        }

        Node<K, V> head = array[index];
        while(head.next != null) {
            if ((head.hash == hash) && (head.key.equals(key))) {
                V oldValue = head.value;
                head.value = value;
                this.size++;
                return oldValue;
            }
        }

        addNodeToBucket(index, hash, key, value);
        return null;
    }

    private void addNodeToBucket(int index, int hash, K key, V value) {
        Node<K, V> newNode = new Node<>(hash, key, value);
        Node<K, V> oldNode = array[index];
        newNode.setNextNode(oldNode);
        array[index] = newNode;
        this.size++;
    }

    private V putForNullKey(V value) {
        Node<K, V> head = array[0];
        while(head.next != null) {
            if (head.key == null) {
                V oldValue = head.value;
                head.value = value;
                this.size++;
                return oldValue;
            }
        }
        addNodeToBucket(0, 0, null, null);
        return null;
    }

}
