import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class LruCacheTest {
    @Test
    public void testNegativeCapacity() {
        Assertions.assertThrows(AssertionError.class,
                () -> new LruCache<>(-1));
    }

    @Test
    public void testZeroCapacity() {
        Assertions.assertThrows(AssertionError.class,
                () -> new LruCache<>(0));
    }

    @Test
    public void testPositiveCapacity() {
        new LruCache<>(10);
    }


    @Test
    public void testGetFromEmptyList() {
        LruCache<Integer, Integer> cache = new LruCache<>(10);
        Assertions.assertNull(cache.get(10));
    }

    @Test
    public void testPutAndGet() {
        LruCache<Integer, Integer> cache = new LruCache<>(3);
        int key = 1;
        int value = 1;
        cache.put(key, value);
        Assertions.assertEquals(value, cache.get(key));
    }

    @Test
    public void testPutManyTimes() {
        LruCache<Integer, Integer> cache = new LruCache<>(3);
        int key = 1;
        IntStream.range(1, 100).forEach(value -> {
            cache.put(key, value);
            Assertions.assertEquals(value, cache.get(key));
        });

    }

    @Test
    public void testSimpleEviction() {
        int capacity = 3;
        LruCache<Integer, Integer> cache = new LruCache<>(capacity);
        IntStream.range(1, capacity + 1).forEach(i -> {
            cache.put(i, i);
        });
        int keyForMoving = 1;
        cache.get(keyForMoving);
        cache.put(4, 4);
        int evictedKey = 2;
        Assertions.assertNull(cache.get(evictedKey));
        Assertions.assertNotNull(cache.get(keyForMoving));

    }

    @Test
    public void testEvictions() {
        int capacity = 3;
        LruCache<Integer, Integer> cache = new LruCache<>(capacity);
        IntStream.range(1, 10 * capacity + 1).forEach(i -> {
            cache.put(i, i);
        });
        IntStream.range(1, 10 * capacity + 1 - capacity).forEach(
                evictedKey -> {
                    Assertions.assertNull(cache.get(evictedKey));
                }
        );
        IntStream.range(10 * capacity - capacity + 1, 10 * capacity + 1).
                forEach(cachedKey ->
                        Assertions.assertNotNull(cache.get(cachedKey))
                );

    }

}
