package labsheetsall;
import java.util.Stack;
public class inftopost {
    private int precedence(char ch) {
    	switch(ch) {
    	case'+':
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
	for (int i = 0; i<expression.length();i++) {
		char c = expression.charAt(i);
		result.append(c);
		if (Character.isLetterOrDigit(c)) {
		}
		else if (c == '(') {
			stack.push(c);
		}
		
		else if (c == ')') {
				
		while (!stack.isEmpty() && stack.peek() != '(') {
			result.append(stack.pop());
		}
		stack.pop();
		}
	
	else {
		while(!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
			result.append(stack.pop());
		}
		stack.push(c);
	}
		
}
	while (stack.isEmpty()) {
		if (stack.peek() == '(') {
			System.out.println("Invalid expression");
		}
		result.append(stack.pop());
	}
	return result.toString();
}
	public static void main(String[] args) {

        inftopost converter = new inftopost();
        String infixExpression = "a+b*(c^d-e)^(f+g*h)-i";
        String postfixExpression = converter.convert(infixExpression);
        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);

	}

}
