package labsheetsall;

public class stak {
	int size; 
	int [] array;
	int top;
	stak(int size){
		this.size = size;
		this.array = new int [size];
		this.top = -1;
	}
	boolean isEmpty() {
		return (top == -1);
	}
	boolean isFull() {
		return (top == size -1);
	}
	int size() {
		return (top +1);
	}		
		}
	}
	public static void main(String[] args) {

	}

}
