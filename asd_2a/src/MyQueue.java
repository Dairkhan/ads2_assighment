// Implements a queue data structure using a linked list as the underlying data structure
public class MyQueue<T> {
    // The linked list to store queue elements
    private MyLinkedList<T> list;

    // Constructor to initialize the queue with an empty linked list
    public MyQueue() {
        this.list = new MyLinkedList<>();
    }

    // Checks if the queue is empty
    public boolean empty() {
        return list.isEmpty();
    }

    // Returns the number of elements in the queue
    public int size() {
        return list.size();
    }

    // Returns the element at the front of the queue without removing it
    // Throws an exception if the queue is empty
    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.getFirst();
    }

    // Adds an element to the back of the queue
    public void enqueue(T item) {
        list.addLast(item);
    }

    // Removes and returns the element at the front of the queue
    // Throws an exception if the queue is empty
    public T dequeue() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T firstItem = list.getFirst();
        list.removeFirst();
        return firstItem;
    }
}