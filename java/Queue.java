import java.util.ArrayList;
import java.util.List;

// This is a Queue representation in Java.
// Queue uses the First-In, First-Out (FIFO) method to organize elements.
// We are implementing the queue using a dynamic array (List/ArrayList).

public class Queue {
    public List<String> queue = new ArrayList<>();

    // This method helps check if the queue is empty
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    // This method adds a value to the end of the queue
    public void enqueue(String value) {
        this.queue.add(value);
    }

    // This method removes and returns the first element in the queue (FIFO)
    public String dequeue() {
        if (this.isEmpty()) {
            return "Queue is empty";
        }

        String firstElement = this.queue.get(0);
        this.queue.remove(0);

        return firstElement;
    }

    // Returns the front element without removing it
    public String peek() {
        if (this.isEmpty()) {
            return "Queue is empty";
        }
        return this.queue.get(0);
    }

    // Returns the total number of elements in the queue
    public int size() {
        return this.queue.size();
    }

    public static void main(String[] args) {
        Queue myQueue = new Queue();

        // 1. Check if empty
        System.out.println("Is queue empty? " + myQueue.isEmpty());

        // 2. Enqueue elements
        myQueue.enqueue("amiola");
        myQueue.enqueue("demilade");
        myQueue.enqueue("mary");
        myQueue.enqueue("grace");

        System.out.println("Queue contents: " + myQueue.queue);
        System.out.println("Front element (peek): " + myQueue.peek());
        System.out.println("Queue size: " + myQueue.size());

        // 3. Dequeue elements in FIFO order
        System.out.println("\nDequeued: " + myQueue.dequeue());
        System.out.println("Queue after 1st dequeue: " + myQueue.queue);

        System.out.println("Dequeued: " + myQueue.dequeue());
        System.out.println("Queue after 2nd dequeue: " + myQueue.queue);

        // 4. Empty the remaining items
        System.out.println("Dequeued: " + myQueue.dequeue());
        System.out.println("Dequeued: " + myQueue.dequeue());

        // 5. Dequeue on empty queue
        System.out.println("\nDequeued from empty queue: " + myQueue.dequeue());
        System.out.println("Is queue empty? " + myQueue.isEmpty());
    }
}
