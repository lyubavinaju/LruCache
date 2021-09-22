import javax.annotation.Nullable;

public class DoublyLinkedList<K, V> {

    @Nullable
    private Node head = null;
    @Nullable
    private Node tail = null;
    private int size = 0;

    class Node {
        private final K key;
        private final V value;
        private boolean deleted;
        @Nullable
        private Node previous;
        @Nullable
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            assert value != null;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void remove() {
            assert !deleted : "This node is already deleted.";
            assert !empty();
            deleted = true;
            size--;
            if (this.previous != null) this.previous.next = this.next;
            if (this.next != null) this.next.previous = this.previous;
            if (this == head) {
                head = head.next;
            }
            if (this == tail) {
                tail = tail.previous;
            }

        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]" + " "
                    + (next == null ? "" : next.toString());
        }
    }

    public Node peekFirst() {
        assert !empty();
        return head;
    }

    public Node addFirst(K key, V value) {
        assert value != null : "Null value isn't allowed here.";
        Node node = new Node(key, value);
        if (empty()) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.previous = node;
            head = node;
        }
        size++;
        return node;
    }

    public Node removeLast() {
        assert !empty() : "List is empty.";
        Node last = tail;
        last.remove();
        return last;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (empty()) return "";
        return head.toString();
    }

    public boolean empty() {
        if (head == null) {
            assert tail == null;
            return true;
        }
        return false;
    }
}
