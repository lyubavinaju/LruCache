import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class DoublyLinkedListTest {
    @Test
    public void testAddSingleElement() {
        DoublyLinkedList<Integer, Integer> list = new DoublyLinkedList<>();
        int key = 1;
        int value = 1;
        list.addFirst(key, value);
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.peekFirst().getValue(), value);
    }

    @Test
    public void testAddManyElements() {
        DoublyLinkedList<Integer, Integer> list = new DoublyLinkedList<>();
        IntStream.range(1, 100).forEach(i -> {
            list.addFirst(i, i);
            Assertions.assertEquals(list.size(), i);
            Assertions.assertEquals(list.peekFirst().getValue(), i);
        });
    }

    @Test
    public void testRemove() {
        DoublyLinkedList<Integer, Integer> list = new DoublyLinkedList<>();
        DoublyLinkedList<Integer, Integer>.Node node1 = list.addFirst(1, 1);
        DoublyLinkedList<Integer, Integer>.Node node2 = list.addFirst(2, 2);
        DoublyLinkedList<Integer, Integer>.Node node3 = list.addFirst(3, 3);

        node2.remove();
        Assertions.assertEquals(list.size(), 2);
        node1.remove();
        Assertions.assertEquals(list.size(), 1);
        node3.remove();
        Assertions.assertEquals(list.size(), 0);


    }

    @Test
    public void testRemoveLast() {
        DoublyLinkedList<Integer, Integer> list = new DoublyLinkedList<>();
        IntStream.range(1, 100).forEach(i -> list.addFirst(i, i));
        IntStream.range(1, 100).forEach(i -> {
            list.removeLast();
            Assertions.assertEquals(list.size(), 100 - i - 1);
        });
    }

    @Test
    public void addNullTest() {
        DoublyLinkedList<Integer, Integer> list = new DoublyLinkedList<>();
        Assertions.assertThrows(AssertionError.class, () -> {
            list.addFirst(null, null);
        });
    }

    @Test
    public void testRemoveFromEmptyList() {
        DoublyLinkedList<Integer, Integer> list = new DoublyLinkedList<>();
        Assertions.assertThrows(AssertionError.class, list::removeLast);
    }

    @Test
    public void testDoubleRemoveTest() {
        DoublyLinkedList<Integer, Integer> list = new DoublyLinkedList<>();
        DoublyLinkedList<Integer, Integer>.Node node1 = list.addFirst(1, 1);
        DoublyLinkedList<Integer, Integer>.Node node2 = list.addFirst(2, 2);
        DoublyLinkedList<Integer, Integer>.Node node3 = list.addFirst(3, 3);
        node2.remove();
        Assertions.assertThrows(AssertionError.class,
                node2::remove);


    }

}
