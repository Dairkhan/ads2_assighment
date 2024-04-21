import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {
    // Node class to represent elements of the linked list
    private class MyNode {
        T data;
        MyNode next;
        MyNode prev;
        // Constructor to initialize node with data
        public MyNode(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private MyNode head; // Reference to the first node in the list
    private MyNode tail; // Reference to the last node in the list
    private int size;    // Current size of the list

    // Constructor to initialize an empty linked list
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    // Checks if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }


    // Adds an element to the end of the list
    @Override
    public void add(T item) {
        addLast(item);
    }
    // Sets the element at the specified index
    @Override
    public void set(int index, T item) {
        MyNode node = getNode(index);
        node.data = item;
    }
    // Adds an element at the specified index
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            MyNode prevNode = getNode(index - 1);
            MyNode newNode = new MyNode(item);
            newNode.next = prevNode.next;
            prevNode.next.prev = newNode;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            size++;
        }
    }
    // Adds an element to the beginning of the list
    @Override
    public void addFirst(T item) {
        MyNode newNode = new MyNode(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    // Adds an element to the end of the list
    @Override
    public void addLast(T item) {
        MyNode newNode = new MyNode(item);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    // Returns the element at the specified index
    @Override
    public T get(int index) {
        return getNode(index).data;
    }
    // Returns the first element of the list
    @Override
    public T getFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        return head.data;
    }
    // Returns the last element of the list9
    @Override
    public T getLast() {
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        return tail.data;
    }
    // Removes the element at the specified index
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            MyNode prevNode = getNode(index - 1);
            prevNode.next = prevNode.next.next;
            prevNode.next.prev = prevNode;
        }
        size--;
    }
    // Removes the first element of the list
    @Override
    public void removeFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        remove(0);
    }
    // Removes the last element of the list
    @Override
    public void removeLast() {
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        remove(size - 1);
    }
    // Sorts the elements of the list
    @Override
    public void sort() {
        throw new UnsupportedOperationException("Sort operation is not supported for doubly linked list");
    }
    // Returns the index of the first occurrence of the specified element in the list
    @Override
    public int indexOf(Object object) {
        MyNode current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(object)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }
    // Returns the index of the last occurrence of the specified element in the list
    @Override
    public int lastIndexOf(Object object) {
        MyNode current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.data.equals(object)) {
                return index;
            }
            current = current.prev;
            index--;
        }
        return -1;
    }
    // Checks if the list contains the specified element
    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }
    // Returns an array containing all of the elements in the list
    @Override
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        MyNode current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    // clear
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    // Returns the number of elements in the list
    @Override
    public int size() {
        return size;
    }
    // Returns an iterator over the elements in the list
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
    // Returns the node at the specified index
    private MyNode getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        MyNode current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }


    // Test method
    public static void main(String[] args) {
        MyLinkedList<Integer> myList = new MyLinkedList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);

        System.out.println("Size: " + myList.size());
        System.out.println("First element: " + myList.getFirst());
        System.out.println("Last element: " + myList.getLast());

        myList.removeFirst();
        System.out.println("After removing first element, first element: " + myList.getFirst());

        myList.addLast(4);
        myList.addFirst(0);

        System.out.println("Index of 3: " + myList.indexOf(3));
        System.out.println("Last index of 4: " + myList.lastIndexOf(4));

        System.out.println("Is 2 in the list? " + myList.contains(2));

        System.out.print("List as array: ");
        Object[] objectArray = myList.toArray();
        Integer[] array = new Integer[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            array[i] = (Integer) objectArray[i];
        }
        for (Integer item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
        myList.clear();
        System.out.println("List cleared, size: " + myList.size());
    }
}