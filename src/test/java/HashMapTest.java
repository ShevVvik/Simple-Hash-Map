import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest {

    @Test
    public void hash() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void initHashMapWrongCapacity() {
        HashMap<Integer, String> map = new HashMap<>(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initHashMapZeroCapacity() {
        HashMap<Integer, String> map = new HashMap<>(0);
    }

    @Test
    public void initHashMap() {
        HashMap<Integer, String> map = new HashMap<>(10);
        int capacity = map.capacity;
        Assert.assertEquals(10, capacity);
        for (int i = 0; i < 10; i++) {
            map.get(i);
        }
    }

    @Test
    public void indexFor() {

    }

    @Test
    public void hashZero() {
        assertEquals(0, HashMap.hash(0));
    }

    @Test
    public void putWithNewKey() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = 10;
        String value = "test";
        map.put(key, value);
        String result = map.get(key);
        Assert.assertEquals(value, result);
    }

    @Test
    public void putWithKey() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key1 = 10;
        String value1 = "test";
        map.put(key1, value1);
        Integer key2 = 10;
        String value2 = "test2";
        map.put(key2, value2);
        String result = map.get(key1);
        Assert.assertEquals(value2, result);
    }

    @Test
    public void putWithAddToList() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key1 = null;
        String value1 = "test";
        map.put(key1, value1);
        Integer key2 = 0;
        String value2 = "test2";
        map.put(key2, value2);
        String result = map.get(key1);
        Assert.assertEquals(value1, result);
    }

    @Test
    public void remove() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void removeNode() {
    }

    @Test
    public void size() {
    }

    @Test
    public void getNode() {
    }

    @Test
    public void testGetNode() {
    }

    @Test
    public void checkKey() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void removeAll() {
    }
}