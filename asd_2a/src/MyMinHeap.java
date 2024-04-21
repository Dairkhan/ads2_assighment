// Implements a min heap data structure using an array list as the underlying data structure
public class MyMinHeap<T extends Comparable<T>> {
    // The array list to store heap elements
    private MyArrayList<T> heap;

    // Constructor to initialize the heap with an empty array list
    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    // Checks if the heap is empty
    public boolean empty() {
        return heap.isEmpty();
    }

    // Returns the number of elements in the heap
    public int size() {
        return heap.size();
    }

    // Returns the minimum element in the heap without removing it
    // Throws an exception if the heap is empty
    public T getMin() {
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    // Removes and returns the minimum element in the heap
    // Throws an exception if the heap is empty
    public T extractMin() {
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(0);
        return min;
    }

    // Inserts a new element into the heap
    public void insert(T item) {
        heap.add(item);
        traverseUp(heap.size() - 1);
    }

    // Restores the heap property by moving an element down the tree
    private void heapifyDown(int index) {
        int leftChild = leftChildOf(index);
        int rightChild = rightChildOf(index);
        int smallest = index;

        if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChild;
        }
        if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // Restores the heap property by moving an element up the tree
    private void traverseUp(int index) {
        while (index > 0 && heap.get(index).compareTo(heap.get(parentOf(index))) < 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    // Calculates the index of the left child of a given index
    private int leftChildOf(int index) {
        return 2 * index + 1;
    }

    // Calculates the index of the right child of a given index
    private int rightChildOf(int index) {
        return 2 * index + 2;
    }

    // Calculates the index of the parent of a given index
    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    // Swaps the elements at the specified indices
    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
