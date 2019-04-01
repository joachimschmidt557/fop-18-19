package Main;

import java.util.ArrayList;

import Exceptions.InvalidTermException;
import Math.DoubleStringMath;

public class Term {

	static {
		
		if (Utils.math == null) {
			Utils.math = new DoubleStringMath();
		}
		
	}
	
	private String term;
	private String result;
	private MathTermExpression parsed;
	
	/*
	// REMOVE LATER
	public static void main(String[] args) {
		
		try {
			Term testTerm = new Term("12 + 4 + 7534 + 7853");
			System.out.println("Res:" + testTerm.getResult());
			testTerm = new Term("14+(443-(44*(1+1.3)))+(4+4)");
			System.out.println("Res:" + testTerm.getResult());
			testTerm = new Term("(1)+(3)+(4)");
			System.out.println("Res:" + testTerm.getResult());
			testTerm = new Term("120");
			System.out.println("Res:" + testTerm.getResult());
			testTerm = new Term("3*2+4-2");
			System.out.println("Res:" + testTerm.getResult());
			testTerm = new Term("(5+5.25)*2-10/(2+1)");
			System.out.println("Res:" + testTerm.getResult());
			testTerm = new Term("0+3.2*(-4+-5)*((2+2)*(1+(6-2.25125)))");
			System.out.println("Res:" + testTerm.getResult());
			testTerm = new Term("4)+4+(");
			System.out.println("Res:" + testTerm.getResult());
		} catch (InvalidTermException e) {
			System.out.println(e.getMessage());
		}
		
	}
	// END REMOVE LATER
	 * 
	 */
	
	/**
	 * Constructs a new object of Term
	 * @param trm A string representation of the term
	 * @throws InvalidTermException
	 */
	public Term(String trm) throws InvalidTermException{
		
		term = removeWhitespace(trm);
		
		parsed = parseString(term);
		
		System.out.println(parsed.ToString());
		
	}
	
	/**
	 * Removes whitespace (tabs, spaces, etc.) from the string
	 * @param str The string to process
	 * @return The processed string
	 */
	private String removeWhitespace(String str) {
		
		String rslt = "";
		for (char c : str.toCharArray()) {
			if (!Character.isWhitespace(c))
				rslt += c;
		}
		return rslt;
		
	}
	
	/**
	 * Returns a prettier representation of the error message
	 * @param msg The error message
	 * @param trm The term
	 * @param index	The index at which the error occurred
	 * @return A pretty error message :)
	 */
	private String prettyError(String msg, String trm, int index) {
		
		String arrow = "";
		for (int i = 0; i < index; i++) arrow += " ";
		arrow += "^";
		
		return msg + System.lineSeparator() + trm + System.lineSeparator() + arrow;
		
	}
	
	/**
	 * Parses the string into a term represented by a class of type
	 * MathTermExpression for easy evaluation. Throws InvalidTermException when
	 * parsing fails.
	 * @param str The string to parse
	 * @return A class representing the term implementing interface MathTermExpression
	 * @throws InvalidTermException
	 */
	private MathTermExpression parseString(String str) throws InvalidTermException {
		
		System.out.println("Parsing: " + str);
		
		// Special case: Empty term
		if (str.equals(""))
			return new AtomicNumber("0");
		
		ArrayList<MathTermExpression> theCurrentExpr = new ArrayList<MathTermExpression>();
		char[] charArray = str.toCharArray();
		
		boolean justEndedAParen = false;
		
		String currentNumber = "";
						
		// Eliminate all parenthesis and fill up the array
		for (int i = 0; i < charArray.length; i++) {
			
			System.out.println("--> " + charArray[i]);
			
			// We are looking at an opening parenthesis
			if (charArray[i] == '(') {
				
				// Is this opening parenthesis legal?
				if (currentNumber != "")
					throw new InvalidTermException(str, prettyError("Detected opening parenthesis in illegal spot", str, i));
				
				// Find the matching closing parenthesis
				// We start our level at 0
				int currentParenthesisLevel = 0;
				int indexOfClosingParen = -1;
				
				for (int j = i+1; j < charArray.length; j++) {
					
					// We found another opening paren
					if (charArray[j] == '(')
						// Increment the level
						currentParenthesisLevel += 1;
					
					// We found a closing paren
					else if (charArray[j] == ')') {
						if (currentParenthesisLevel > 0)
							// Decrement the level
							currentParenthesisLevel -= 1;
						else if (currentParenthesisLevel == 0) {
							// We found the matching closing parenthesis
							
							// Throw error if we have an empty parenthesis
							//if (j == i+1)
								//throw new InvalidTermException(str, prettyError("Found \"()\", invalid parenthesis", str, i));
							
							indexOfClosingParen = j;
							break;
						}
						else
							throw new InvalidTermException(str,
									prettyError("Parenthesis mismatch", str, i));
					}
					
				}
				
				// If we didn't find a closing parenthesis, throw an error
				if (indexOfClosingParen == -1)
					throw new InvalidTermException(str, prettyError("Missing closing parenthesis", str, i));
				
				// Parse the contents of the parenthesis
				// This is a recursive call of the function
				theCurrentExpr.add(parseString(str.substring(i + 1, 
										indexOfClosingParen)));
				
				// Skip to after the parenthesis
				System.out.println("IndexOfClosingParen: " + indexOfClosingParen);
				System.out.println(charArray[indexOfClosingParen]);
				i = indexOfClosingParen;
				justEndedAParen = true;
				
			}
			// We are not looking at an opening paren
			else {
				
				// Parse numbers and operators
				
				// Are we at the start of a number to parse?
				if (currentNumber.equals("") && !justEndedAParen) {
					
					System.out.println("NumStart" + charArray[i]);
					
					// Check if no invalid character starts this number
					if (!(Character.isDigit(charArray[i]) || (charArray[i] == '-')))
						throw new InvalidTermException(str, prettyError("Invalid start of number detected", str, i));
					
					currentNumber += Character.toString(charArray[i]);
					
					// Check if this is the last character
					if (i == charArray.length - 1) {
						if (currentNumber.equals("-"))
							throw new InvalidTermException(str, prettyError("Number cannot consist of only a minus", str, i));
						theCurrentExpr.add(new AtomicNumber(currentNumber));
					}
					
				}
				// We are not at the start of a number
				else {
					
					System.out.println("at: "+charArray[i]);
					
					// Continue parsing number
					// This character is a digit
					if (Character.isDigit(charArray[i])) {
						
						currentNumber += Character.toString(charArray[i]);
						
						// Check if this is the last character
						if (i == charArray.length - 1) {
							if (currentNumber.equals("-"))
								throw new InvalidTermException(str, prettyError("Number cannot consist of only a minus", str, i));
							theCurrentExpr.add(new AtomicNumber(currentNumber));
						}
						
					}
					// This character is not a digit
					else {
						if (justEndedAParen) {
							
							justEndedAParen = false;
							if ((charArray[i] == '+') ||
								(charArray[i] == '-') ||
								(charArray[i] == '*') ||
								(charArray[i] == '/')) {

								theCurrentExpr.add(new Symbol(Character.toString(charArray[i])));
								
								// Parse the next number
								currentNumber = "";
							}
							else
								throw new InvalidTermException(str, prettyError("Missing operator after parenthesis", str, i));
						}
						
						// Are we reading a . ?
						else if (charArray[i] == '.') {

							if (currentNumber.contains("."))
								throw new InvalidTermException(str, prettyError("Detected more than one . in a number", str, i));
							else
								currentNumber += Character.toString(charArray[i]);
						}

						// We have reached the end of a wor(l)d due to an operator
						else if ((charArray[i] == '+') ||
								(charArray[i] == '-') ||
								(charArray[i] == '*') ||
								(charArray[i] == '/')) {

							System.out.println("uu");
							// Finished parsing number
							
							if (currentNumber.equals("-"))
								throw new InvalidTermException(str, prettyError("Number cannot consist of only a minus", str, i));
							
							theCurrentExpr.add(new AtomicNumber(currentNumber));
							theCurrentExpr.add(new Symbol(Character.toString(charArray[i])));
							
							// Parse the next number
							currentNumber = "";
						}
						
						// We read an invalid character
						else
							throw new InvalidTermException(str, prettyError("Detected invalid character in term", str, i));
						
					}
					
				}
				
			}

		}
		
		System.out.print("Bef*" + theCurrentExpr.size() + "{");
		
		for (MathTermExpression expr : theCurrentExpr) {
			System.out.print(expr.ToString() + ", ");
		}
		System.out.println("}");
		
		// First calculate * and /
		boolean expressionContainsMulOrDiv = true;
		
		// Until no more * and / are left in the term
		while (expressionContainsMulOrDiv) {
			
			for (int i = 0; i < theCurrentExpr.size(); i++) {
				
				if (theCurrentExpr.get(i) instanceof Symbol) {
					
					if ((theCurrentExpr.get(i).eval().equals("*")) || 
						(theCurrentExpr.get(i).eval().equals("/"))) {
						
						// The current element we are looking at is a symbol
						
						// Check we don't try to access a nonexistent element
						if ((i+1) >= theCurrentExpr.size())
							throw new InvalidTermException(str, prettyError("The last operator is lonely and misses a number", 
									str, charArray.length));
						
						// Create a new binary operation
						theCurrentExpr.set(i, new OperationExpression(theCurrentExpr.get(i-1),
												theCurrentExpr.get(i+1), 
												theCurrentExpr.get(i).eval()));
						
						// Remove the unnecessary elements of the array
						theCurrentExpr.remove(i+1);
						theCurrentExpr.remove(i-1);
						
					}

				}
				
			}
			
			// Check if there are still * and / left in the term
			expressionContainsMulOrDiv = false;
			for (MathTermExpression expr : theCurrentExpr) {
				
				if (expr instanceof Symbol) {
					
					if ((expr.eval().equals("*")) || 
						(expr.eval().equals("/"))) {
						
						expressionContainsMulOrDiv = true;
						break;
						
					}
					
				}
				
			}
			
		}
		
		System.out.print("Bef+" + theCurrentExpr.size() + "{");
		
		for (MathTermExpression expr : theCurrentExpr) {
			System.out.print(expr.ToString() + ", ");
		}
		System.out.println("}");
		
		// Then calculate + and -
		boolean expressionContainsPlusOrMinus = true;
		
		// Until no more + and - are left in the term
		while (expressionContainsPlusOrMinus) {
		
			for (int i = 0; i < theCurrentExpr.size(); i++) {
				
				if (theCurrentExpr.get(i) instanceof Symbol) {
									
					if ((theCurrentExpr.get(i).eval().equals("+")) || 
						(theCurrentExpr.get(i).eval().equals("-"))) {
											
						// The current element we are looking at is a symbol
						
						// Check we don't try to access a nonexistent element
						if ((i+1) >= theCurrentExpr.size())
							throw new InvalidTermException(str, prettyError("The last operator is lonely and misses a number", 
									str, charArray.length));
						
						theCurrentExpr.set(i, new OperationExpression(theCurrentExpr.get(i-1),
												theCurrentExpr.get(i+1), 
												theCurrentExpr.get(i).eval()));
						
						// Remove the unnecessary elements of the array
						theCurrentExpr.remove(i+1);
						theCurrentExpr.remove(i-1);
						
					}

				}
				
			}
			
			// Check if there are still + and - left in the term
			expressionContainsPlusOrMinus = false;
			for (MathTermExpression expr : theCurrentExpr) {
				
				if (expr instanceof Symbol) {
					
					if ((expr.eval().equals("+")) || 
						(expr.eval().equals("-"))) {
						
						expressionContainsPlusOrMinus = true;
						break;
						
					}
					
				}
				
			}
			
		}
		
		System.out.print("Aft " + theCurrentExpr.size() + "{");
		
		for (MathTermExpression expr : theCurrentExpr) {
			System.out.print(expr.ToString() + ", ");
		}
		System.out.println("}");
		
		// If the array contains more than 1 element,
		// the term was invalid
		if (theCurrentExpr.size() > 1)
			throw new InvalidTermException(str, prettyError("The term contains expressions which are not evaluated. " +
												"This is likely due to wrong parenthesis placement.", str, 0));
		
		System.out.println("End parsing "+str);
		
		return theCurrentExpr.get(0);
		
	}
	
	/**
	 * This interface represents a mathematical expression
	 * @author joachim
	 *
	 */
	public interface MathTermExpression {
		
		/**
		 * Evaluates this expression
		 * @return The exact expression in a string
		 */
		public String eval();
		
		/**
		 * Gives a human-readable representation of this expression
		 * @return A more human-readable expression of the expression
		 */
		public String ToString();
		
	}
	
	/**
	 * This class represents one number
	 * @author joachim
	 *
	 */
	public class AtomicNumber implements MathTermExpression {
		
		private String number;
		
		/**
		 * Constructs a new object representing a number
		 * @param num The number
		 */
		public AtomicNumber(String num) {
			number = num;
		}
		
		/**
		 * Returns the number
		 */
		public String eval() {
			return number;
		}
		
		/**
		 * Returns "a(NUMBER)"
		 */
		public String ToString() {
			return "a(" + number + ")";
		}
		
	}
	
	/**
	 * This class represents one symbol (+, -, *, /)
	 * @author joachim
	 *
	 */
	public class Symbol implements MathTermExpression {
		
		private String symbol;
		
		/**
		 * Constructs a new object representing a symbol
		 * @param symb The symbol
		 */
		public Symbol(String symb) {
			symbol = symb;
		}
		
		/**
		 * Returns the symbol
		 */
		public String eval() {
			return symbol;
		}
		
		/**
		 * Returns "s(SYMBOL)"
		 */
		public String ToString() {
			return "s(" + symbol + ")";
		}
		
	}
	
	/**
	 * This class represents a binary operation
	 * @author joachim
	 *
	 */
	public class OperationExpression implements MathTermExpression {
		
		private MathTermExpression value1, value2;
		private String operation;

		/**
		 * Constructs a new object representing a
		 * binary operation
		 * @param exp1 The left value
		 * @param exp2 The right value
		 * @param op The operation (a symbol)
		 */
		public OperationExpression(MathTermExpression exp1, MathTermExpression exp2,
				String op) {
			
			value1 = exp1; value2 = exp2;
			operation = op;
			
		}
		
		/**
		 * Calculates the result of the operation
		 */
		public String eval() {
			if (operation.equals("+"))
				return Utils.math.add(value1.eval(), value2.eval());
			if (operation.equals("-"))
				return Utils.math.sub(value1.eval(), value2.eval());
			if (operation.equals("*"))
				return Utils.math.mul(value1.eval(), value2.eval());
			if (operation.equals("/"))
				return Utils.math.div(value1.eval(), value2.eval());
			else return "";
		}
		
		/**
		 * Returns "e(LEFTVALUE OPERATOR RIGHTVALUE)"
		 */
		public String ToString() {
			return "e(" + value1.ToString() + operation + value2.ToString() + ")";
		}
		
	}
	
	/**
	 * Returns the result of the term
	 * @return A string representation of the result
	 */
	public String getResult() {
		
		// Return result if it is already determined
		if (result != null)
			return result;
		
		// Else calculate result
		result = parsed.eval();
		
		return result;
		
	}
	
}
