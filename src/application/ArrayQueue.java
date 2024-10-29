package application;

public class ArrayQueue {
    private int[] queue;
    private int maxSize;
    private int front;   
    private int rear;   
    private int currentSize; 

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new int[this.maxSize];
        this.front = 0;
        this.rear = -1;
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
            rear = (rear + 1) % maxSize; 
            queue[rear] = element;
            currentSize++;
            System.out.println("Inserted " + element);
        } else {
            System.out.println("Queue is full!");
        }
    }
    public int dequeue() {
        if (!isEmpty()) {
            int temp = queue[front];
            front = (front + 1) % maxSize; 
            currentSize--;
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
        ArrayQueue queue = new ArrayQueue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

     
        queue.enqueue(6);

        queue.dequeue();
        queue.dequeue();
        queue.peek();

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

     
        queue.dequeue();
    }

}
