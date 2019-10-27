
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
        while (head.next != null) {
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
        while (head.next != null) {
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
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Node getNode(Object key) {
        int hash = hash(key);
        int index = indexFor(hash, capacity);
        Node node = array[index];
        while (node != null) {
            if (node.hash == hash && node.key.equals(key)) return node;
            node = node.next;
        }
        return null;
    }

    @Override
    public Node getNode(int index) {
        return array[index];
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
