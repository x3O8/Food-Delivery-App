package labsheetsall;

class Node{
	int data;
	Node left, right;}
	Node (int n){
		right = null;
		left = null;
		data = n;
	}
}
public class BST {
	Node root;

	public void insert(int data) {
		root = insertRec(root, data);
		
	}
	public Node insertRec(Node root, int data) {
		if (root == null) {
			root = new Node(data);
			
		}
		else if (root.data > data) {
			root.left = insertRec(root.left, data);
		}
		else if (root.data < data) {
			root.right = insertRec(root.right,data);
		}
		return root;
	}
	public void inorder() {
		inorderRec(root);
	}
	public void inorderRec(Node root) {
		if (root != null) {
			inorderRec(root.left);
			System.out.print(root.data+ " ");
			inorderRec(root.right);
		}
	}
	public void postorder() {
		postorderRec(root);
	}
	public void postorderRec(Node root) {
		if (root != null) {
			postorderRec(root.left);
			postorderRec(root.right);
			System.out.print(root.data+" ");
		}
	}
	public void preorder() {
		preorderRec(root);
	}
	public void preorderRec(Node root) {
		if (root != null) {
			
			System.out.print(root.data+" ");
			preorderRec(root.left);
			preorderRec(root.right);
			
		}
	}
	
	public static void main(String[] args) {
		BST btree = new BST();
		btree.insert(4);
		btree.insert(21);
		btree.insert(6);
		btree.insert(8);
		btree.postorder();
		btree.inorder();
		btree.preorder();

	}

}
