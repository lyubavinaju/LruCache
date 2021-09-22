import javax.annotation.Nullable;
import java.util.HashMap;

public class LruCache<K, V> extends AbstractLruCache<K, V> {
    private final DoublyLinkedList<K, V> list = new DoublyLinkedList<>();
    private final HashMap<K, DoublyLinkedList<K, V>.Node> keyToNode = new HashMap<>();

    public LruCache(int capacity) {
        super(capacity);
    }

    @Override
    @Nullable
    public V get(K key) {
        assert key != null;
        if (!keyToNode.containsKey(key)) return null;
        DoublyLinkedList<K, V>.Node node = keyToNode.get(key);
        put(key, node.getValue());
        assert keyToNode.containsKey(key);
        assert keyToNode.get(key).getValue().equals(node.getValue());
        assert keyToNode.get(key) == list.peekFirst();
        return node.getValue();
    }

    @Override
    public void put(K key, V value) {
        assert key != null && value != null;
        if (keyToNode.containsKey(key)) {
            keyToNode.get(key).remove();
        }
        DoublyLinkedList<K, V>.Node node = list.addFirst(key, value);
        keyToNode.put(key, node);

        if (list.size() > capacity) {
            DoublyLinkedList<K, V>.Node deletedNode = list.removeLast();
            keyToNode.remove(deletedNode.getKey());
        }

        assert list.size() <= capacity;
        assert keyToNode.containsKey(key);
        assert keyToNode.get(key).getValue().equals(value);
        assert keyToNode.get(key) == list.peekFirst();

    }

}
