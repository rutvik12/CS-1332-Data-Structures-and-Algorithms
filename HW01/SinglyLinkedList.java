
/**
 * Your implementation of a non-circular SinglyLinkedList with a tail pointer.
 *
 * @author - Rutvik Marakana
 * @version 1.0
 * @userid - rmarakana3
 * @GTID - 903401647
 */
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index.
     * <p>
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index to add the new element
     * @param data  the data to add
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index cannot be less than 0 or greater than size");
        }
        if (data == null) {
            throw new IllegalArgumentException("The data to be added in the node cannot be null");
        }
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
        if (index == 0) {
            addToFront(newNode.getData());
        } else if (index == size) {
            addToBack(newNode.getData());
        } else {
            SinglyLinkedListNode<T> curr = head;
            int count = 0;
            while (count < index - 1) {
                curr = curr.getNext();
                count++;
            }
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
            size++;
        }

    }

    /**
     * Adds the element to the front of the list.
     * <p>
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {

        if (data == null) {
            throw new IllegalArgumentException("The data to be added in the node cannot be null");
        }
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;

    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {

        if (data == null) {
            throw new IllegalArgumentException("The data to be added in the node cannot be null");
        }
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);

        if (head == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other
     * cases.
     *
     * @param index the index of the element to remove
     * @return the data that was removed
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index cannot be less than 0 or greater than or equal to size");
        }
        if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            SinglyLinkedListNode<T> curr = head;
            int count = 0;
            while (count < index - 1) {
                curr = curr.getNext();
                count++;
            }
            T temp = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            size--;
            return temp;
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {

        if (head == null) {
            throw new NoSuchElementException("The list is empty so no data can be removed from front");
        }
        T data = head.getData();
        if (size == 1) {
            head = null;
            tail = null;
            size = 0;
        } else {
            head = head.getNext();
            size--;
        }
        return data;

    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {

        if (head == null) {
            throw new NoSuchElementException("The list is empty so no data can be removed from back");
        } else if (head.getNext() == null) {
            T data = head.getData();
            head = null;
            tail = null;
            size--;
            return data;
        } else {
            SinglyLinkedListNode<T> curr = head;

            while (curr.getNext().getNext() != null) {
                curr = curr.getNext();
            }
            T data = curr.getNext().getData();
            curr.setNext(null);
            tail = curr;
            size--;
            return data;
        }

    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index cannot be a negative number or greater than or equal to size");
        }
        if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            SinglyLinkedListNode<T> curr = head;
            int counter = 0;
            while (counter < index) {
                curr = curr.getNext();
                counter++;
            }
            return curr.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data to be removed is null so nothing is removed");
        }
        if (size == 0) {
            throw new NoSuchElementException("The list is empty so no data can be removed");
        }

        SinglyLinkedListNode<T> curr = head;
        SinglyLinkedListNode<T> temp = null;
        SinglyLinkedListNode<T> prev = null;
        boolean detected = false;

        while (curr != null) {
            if (curr.getNext() != null && curr.getNext().getData().equals(data)) {

                prev = curr;
                temp = curr.getNext();
                detected = true;
            }
            curr = curr.getNext();
        }
        if (head != null) {
            if (temp == null && head.getData() == data) {
                temp = head;
                detected = true;
            }
        }

        if (detected) {
            SinglyLinkedListNode<T> remove;
            if (size == 1 && prev == null) {
                remove = head;
                head = null;
                tail = null;
                size = 0;
            } else if (temp == head && prev == null) {
                remove = head;
                head = head.getNext();
                size--;
            } else if (temp == tail) {
                remove = tail;
                prev.setNext(tail.getNext());
                tail = prev;
                size--;
            } else {
                remove = temp;
                prev.setNext(temp.getNext());
                temp.setNext(null);
                size--;
            }
            return remove.getData();
        } else {
            throw new NoSuchElementException("Data to be removed is not found in the list");
        }

    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * * nodes) in the list in the same order
     */
    public T[] toArray() {

        T[] arr = (T[]) new Object[size];
        int counter = 0;
        SinglyLinkedListNode<T> curr = head;

        while (curr != null) {
            arr[counter] = curr.getData();
            counter++;
            curr = curr.getNext();
        }
        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
