package labsheetsall;

public class cirqueue {
	int front;
	int rear;
	int size;
	int array[];
	cirqueue(int c){
		front = rear = -1;
		size = c;
		array = new int[size];
	}
	public boolean isEmpty() {
		return (front == -1);
	}
	public boolean isFull() {
		return ((rear+1)%size == front);
	}
	public void enqueue(int data) {
		if (isFull()) {
			System.out.println("est full soz");
		}
		if (isEmpty()) {
			front = 0;
		}
		rear = (rear+1)%size;
		array[rear] = data;
		
	}
	public void dequeue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
		}
		else {
			int item = array[front];
			if (front == rear) {
				front = -1;
				rear = -1;
			}
			else {
				front = (front+1)%size;
			}
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
