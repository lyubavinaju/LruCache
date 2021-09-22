
public abstract class AbstractLruCache<K, V> {
    protected final int capacity;

    public AbstractLruCache(int capacity) {
        assert capacity > 0 : "Capacity should be greater than 0.";
        this.capacity = capacity;
    }

    public abstract V get(K key);

    public abstract void put(K key, V value);


}
