// Implements a stack data structure using a linked list as the underlying data structure
public class MyStack<T> {
    // The linked list to store stack elements
    private MyLinkedList<T> list;

    // Constructor to initialize the stack with an empty linked list
    public MyStack() {
        this.list = new MyLinkedList<>();
    }

    // Checks if the stack is empty
    public boolean empty() {
        return list.isEmpty();
    }

    // Returns the number of elements in the stack
    public int size() {
        return list.size();
    }

    // Returns the element at the top of the stack without removing it
    // Throws an exception if the stack is empty
    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.getLast();
    }

    // Pushes an element onto the top of the stack
    public void push(T item) {
        list.addLast(item);
    }

    // Removes and returns the element at the top of the stack
    // Throws an exception if the stack is empty
    public T pop() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T lastItem = list.getLast();
        list.removeLast();
        return lastItem;
    }
}