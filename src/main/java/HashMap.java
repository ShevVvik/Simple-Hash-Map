import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {

    static final int DEFAULT_CAPACITY = 8;

    static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node next;

        Node() {};

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public void setNextNode(Node<K, V> node) {
            this.next = node;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash &&
                    Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash, key, value, next);
        }
    }

    static int hash(Object key) {
        return (key == null) ? 0 : key.hashCode();
    }

    static int indexFor(int hash, int size) {
        return hash & (size - 1);
    }

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException();
        this.array = new Node[capacity];
        this.capacity = capacity;
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
            addNodeToBucket(index, hash, key, value);
            return null;
        }

        Node<K, V> head = array[index];
        while (head != null) {
            if ((head.hash == hash) && (key.equals(head.key))) {
                V oldValue = head.value;
                head.value = value;
                this.size++;
                return oldValue;
            }
            head = head.next;
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
        while (head != null) {
            if (head.key == null) {
                V oldValue = head.value;
                head.value = value;
                this.size++;
                return oldValue;
            }
        }
        addNodeToBucket(0, 0, null, value);
        return null;
    }

    public void remove(K key) {
        removeNode(hash(key), key);
    }

    public void remove(Node<K, V> node) {
        removeNode(hash(node.key), node.key);
    }

    void removeNode(int hash, K key) {
        int index = indexFor(hash, capacity);
        if (this.array[index] == null) return;

        Node<K, V> nodeCurrent = array[index];
        Node<K, V> nodeBefore = null;
        while (nodeCurrent != null) {
            if (nodeCurrent.hash == hash && nodeCurrent.key.equals(key)) {
                if (nodeBefore == null) {
                    array[index] = nodeCurrent.next;
                } else {
                    nodeBefore.next = nodeCurrent.next;
                }
            }
            nodeBefore = nodeCurrent;
            nodeCurrent = nodeCurrent.next;
        }
        this.size--;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(hash(key), key);
        return (node == null) ? null : node.value;
    }

    Node getNode(int hash, K key) {
        int index = indexFor(hash, capacity);
        Node node = array[index];

        while (node != null) {
            if ((node.hash == hash) && ((key == node.key) || (key != null && key.equals(node.key)))) return node;
            node = node.next;
        }
        return null;
    }

    @Override
    public boolean checkKey(Object key) {
        int hash = hash(key);
        int index = indexFor(hash, capacity);
        Node node = array[index];
        while (node != null) {
            if (node.hash == hash && node.key.equals(key)) return true;
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void removeAll() {
        for (int i = 0; i < capacity; i++) {
            array[i] = null;
        }
        this.size = 0;
    }
}
