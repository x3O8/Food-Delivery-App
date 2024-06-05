package labsheetsall;
import java.util.Stack;
public class secondinfpost {
	private int precedence(char ch) {
		switch(ch) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		
		
		}
		return -1;
	}
public String convert(String expression) {
	StringBuilder result = new StringBuilder();
	Stack<Character> stack = new Stack<>();
	for (int i = 0; i < expression.length();i++) {
		char c = expression.charAt(i);
		if (Character.isLetterOrDigit(c)) {
			result.append(c);
		}
		else if (c == '(') {
			stack.push(c);
		
		}
		else if (c == ')') {
			while (!stack.isEmpty() && c != '(') {
				result.append(stack.pop());
			}
		}
		else {
			while(!stack.isEmpty() && precedence(c) < precedence(stack.peek())) {
				result.append(stack.pop());
			}
			stack.push(c);
		}
	}
	while (!stack.isEmpty()) {
		if (stack.peek()== ')') {
			System.out.println("invalid ");
			
		}
		else {
			result.append(stack.pop());
		}
	}
return result.toString();
}
	public static void main(String[] args) {
		secondinfpost plep = new secondinfpost();
		String infixExpression = "a+b*(c^d-e)^(f+g*h)-i";
		String conv = plep.convert(infixExpression);
		System.out.println(conv);
		}

	}


