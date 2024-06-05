package labsheetsall;

public class slinkedlsit {
class Node{
	int data;
	Node next;
	Node prev;
	public Node(int data){
		this.data = data;
		this.next = null;
		this.prev = null;
	}
}
Node head = null;

public void insert(int pos, int data) {
	
	if (pos < 0) {
		return;
	}
	Node newnode = new Node(data);
	if (pos == 0) {
		if (head == null) {
			head = newnode;
			head.next= head;
			head.prev= head;	
		
		}	
	
	else {
		Node tail = head.prev;
		newnode.next = head;
		head.prev = newnode;
		
		tail.next = newnode;
		newnode.prev = tail;
		head = newnode;
	}
		return;	
	}
	else {
	int currentPos = 0;
	Node current = head;
	while (current != null && currentPos < pos -1) {
		current = current.next;
		currentPos ++;
	
	if (current == head) {
		System.out.println("out of bounds");
		return;
	}
	}
	
	newnode.prev = current;
	newnode.next = current.next;
	current.next.prev = newnode;
	current.next = newnode;
}
}
public void insertFirst(int data) {
	insert(0, data);
}
public void search(int data) {
	Node current = head;
	while (current != null && current.data != data) {
		current = current.next;
		
	}
	if (current != null) {
		System.out.println("found");
	}
	else 
		System.out.println("Not found");
}
public void delete(int pos) {
	if (pos < 0) {
		return;
	}
	
	if (head == null) {
		System.out.println("List is empty");
		return;
	}
	if (pos == 0) {
		if (head.next != null) {
			head.next.prev = null;
		}
		head = head.next;
		return;
	
	}

		Node current = head;
		int currentPos = 0;
		while (current != null && currentPos < pos -1) {
			current = current.next;
			currentPos ++;
		}
		if (current == head) {
			System.out.println("out of bounds");
			return;
		}
		
		current.next = current.next.next;
		if (current.next.next != null) {
			current.next.next.prev = current;
		}
	}

public void displaylist() {

	Node current = head;
    do {
        System.out.print(current.data + " -> ");
        current = current.next;
    } while (current != head);
    System.out.println("(head)");
}
	public static void main(String[] args) {
		slinkedlsit slist = new slinkedlsit();
		slist.insertFirst(4);
		slist.insertFirst(5);
		slist.insertFirst(6);
		slist.insert(2, 7);
		slist.search(5);
		slist.displaylist();
		slist.delete(2);
		slist.displaylist();

	}
}

