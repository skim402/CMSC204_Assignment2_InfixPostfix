import java.util.*;
/**
 * Converter between infix and postfix expressions, and evaluating of postfix expression
 * @author Sungmin Kim
 * @semester Fall 2021
 * @class CMSC 204
 * @assignment2 infix and postfix
 * @date 2021/10/03
 */
public class Notation {
	/** Constructor. */
	public Notation() {
		
	}//end constructor
	
	/**	Evaluates postfix expression	
	 * @param postfix to be evaluated
	 * @return result of the evaluation
	 * @throws InvalidNotationFormatException when the postfix is invalid expression. */
	public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException{
		NotationStack<Double> stack = new NotationStack<Double>();
		try {
			for(int index = 0; index < postfix.length() ; index++ ) {
				
				switch (postfix.charAt(index)) {
				case '+':
					stack.push(stack.pop() + stack.pop());
					break;
				case '-':
					stack.push(0 - stack.pop() + stack.pop());
					break;
				case '*':
					stack.push(stack.pop() * stack.pop());
					break;
				case '/':
					stack.push(1 / stack.pop() * stack.pop());
					break;
				default:
					stack.push((double)postfix.charAt(index)-48);
				}//end switch
			}//end for
			return stack.pop();
		}catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		}catch (StackOverflowException e) {
			System.out.println(e.getMessage());
			return 0;
		}//end try catch
	}//end evaluatePostfixExpression
	
	
	/**	Converts infix expression to postfix expression	
	 * @param infix to be converted
	 * @return result of the converting
	 * @throws InvalidNotationFormatException when the input infix expression is invalid. */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		NotationStack<String> stack = new NotationStack<String>();
		NotationQueue<String> queue = new NotationQueue<String>();
		try {
			for (int index = 0; index < infix.length(); index++) {
				switch(infix.charAt(index)) {
				case ' ':
					break;
				case '+': case '-': case '*': case '/':
					if (stack.isEmpty()) {
						stack.push(Character.toString(infix.charAt(index)));
					}else {
						boolean flag = false;
						while(!stack.isEmpty() && !flag) {
							if (infix.charAt(index) == '+' || infix.charAt(index) == '-') {
								if(stack.top() == "*" || stack.top() == "/") {
									queue.enqueue(stack.pop());
								}else {
									flag = true;
								}//end if else
							}else {
								flag = true;
							}//end if else
						}
						stack.push(Character.toString(infix.charAt(index)));
					}//end if else
					break;
				case '(':
					stack.push(Character.toString(infix.charAt(index)));
					break;
				case ')':
					String topOperator = stack.pop();
					while(!topOperator.equals("(")){
						queue.enqueue(topOperator);
						topOperator = stack.pop();
					}
					break;
				default:
					queue.enqueue(Character.toString(infix.charAt(index)));
				}//end switch
			}//end for
			while (!stack.isEmpty()) {
				queue.enqueue(stack.pop());
			}//end while
			String result = "";
			while (!queue.isEmpty()) {
				result += queue.dequeue();
			}//end while
			return result;
		}catch (StackOverflowException e) {
			System.out.println(e.getMessage());
			return "";
		}catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		}catch (QueueUnderflowException e) {
			System.out.println(e.getMessage());
			return "";
		}catch (QueueOverflowException e) {
			System.out.println(e.getMessage());
			return "";
		}//end try catch
	}//end convertInfixToPostfix
	
	/**	Converts postfix expression to infix expression	
	 * @param postfix to be converted
	 * @return result of the converting
	 * @throws InvalidNotationFormatException when the input expression is invalid. */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		NotationStack<String> stack = new NotationStack<String>();
		try {
			for(int index = 0; index < postfix.length(); index++) {
				switch(postfix.charAt(index)) {
				case ' ':
					break;
				case '+': case '-': case '*': case '/':
					String operand2 = stack.pop();
					String operand1 = stack.pop();
					stack.push("(" + operand1 + postfix.charAt(index) + operand2 + ")");
					break;
				default:
					stack.push(Character.toString(postfix.charAt(index)));
				}
			}//end for
			String result = stack.pop();
			if (!stack.isEmpty())
				throw new InvalidNotationFormatException();
			else
				return result;
		}catch (StackOverflowException e) {
			System.out.println(e.getMessage());
			return "";
		}catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		}//end try catch
	}//end convertPostfixToInfix
}//end Notation


