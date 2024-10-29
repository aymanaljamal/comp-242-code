package application;

public class ArrayQueues {
    private int[] queue;
    private int maxSize;
    private int front;
    private int rear;
    private int currentSize;

    public ArrayQueues(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new int[this.maxSize];
        this.front = 0;
        this.rear = -1; // Start at -1 to indicate that queue is initially empty
        this.currentSize = 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void enqueue(int element) {
        if (!isFull()) {
            if (rear < maxSize - 1) { // Only move rear forward if not at end of array
                rear++;
                queue[rear] = element;
                currentSize++;
                System.out.println("Inserted " + element);
            }
        } else {
            System.out.println("Queue is full!");
        }
    }

    public int dequeue() {
        if (!isEmpty()) {
            int temp = queue[front];
            front++;
            currentSize--;
            if (front > rear) { // Reset front and rear if all elements are removed
                front = 0;
                rear = -1;
            }
            System.out.println("Removed " + temp);
            return temp;
        } else {
            System.out.println("Queue is empty!");
            return -1;
        }
    }

    public int peek() {
        if (!isEmpty()) {
            return queue[front];
        } else {
            System.out.println("Queue is empty!");
            return -1;
        }
    }

    public static void main(String[] args) {
        ArrayQueueC queue = new ArrayQueueC(5);

        // Testing enqueues and dequeues
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        // Attempt to enqueue beyond capacity
        queue.enqueue(6);

        // Dequeue several elements
        queue.dequeue(); // 1 out
        queue.dequeue(); // 2 out
        System.out.println("Front element: " + queue.peek()); // Should be 3

        // Remove all elements
        queue.dequeue(); // 3 out
        queue.dequeue(); // 4 out
        queue.dequeue(); // 5 out

        // Attempt to dequeue from empty queue
        queue.dequeue();
    }
}
