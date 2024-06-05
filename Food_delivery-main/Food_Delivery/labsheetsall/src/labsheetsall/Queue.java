package labsheetsall;

public class Queue {
    private int front;
    private int rear;
    private int size;
    private int[] queue;

    // Constructor to initialize the queue
    Queue(int c) {
        front = rear = -1;
        size = c;
        queue = new int[size];
    }

    // Method to add an element to the queue
    public void enqueue(int data) {
        if (rear == size - 1) {
            System.out.println("Queue is full. Cannot enqueue.");
            return;
        }
        if (front == -1 && rear == -1) {
            front = rear = 0;
        } else {
            rear++;
        }
        queue[rear] = data;
    }

    // Method to remove an element from the queue
    public void dequeue() {
        if (front == -1 && rear == -1) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return;
        }
        System.out.println("Popped value: " + queue[front]);
        if (front == rear) {
            front = rear = -1;
        } else {
            front++;
        }
    }

    // Method to display the elements of the queue
    public void display() {
        if (front == -1 && rear == -1) {
            System.out.println("Queue is empty");
            return;
        }
        for (int i = front; i <= rear; i++) {
            System.out.print(queue[i] + " -> ");
        }
        System.out.println();
    }

    // Method to peek at the front element of the queue
    public void peek() {
        if (front == -1 && rear == -1) {
            System.out.println("Queue is empty. Cannot peek.");
            return;
        }
        System.out.println("Front element: " + queue[front]);
    }

    public static void main(String[] args) {
        // Create a queue of capacity 5
        Queue q = new Queue(5);

        // Enqueue elements
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);

        // Display elements
        q.display();

        // Peek at the front element
        q.peek();

        // Dequeue elements
        q.dequeue();
        q.dequeue();

        // Display elements after dequeue
        q.display();

        // Peek at the front element after dequeue
        q.peek();
    }
}
