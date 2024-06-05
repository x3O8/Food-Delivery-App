package labsheetsall;

import java.util.*;

public class brackets {
public static boolean isBalanced(String expression) {
	Stack<Character> stack= new Stack<>();
	for (char bracket : expression.toCharArray()) {
		if (bracket == '{' || bracket == '(' || bracket == '[') {
			stack.push(bracket);
		}
		char top = stack.pop();
		if (bracket == '(' && top != ')' || )
	}
	return true;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
