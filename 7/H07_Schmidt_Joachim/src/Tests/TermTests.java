package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import Exceptions.InvalidTermException;
import Main.Term;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;


public class TermTests {
	
	/**
	 * Test a valid term using all operators
	 * @throws InvalidTermException
	 */
	@Test
	public void checkValidTerm1() throws InvalidTermException {

		Term test = new Term(" 3 / 3 + 3 - 3 * 3 ");

	}
	
	/**
	 * Test a valid term using many parenthesis
	 * @throws InvalidTermException
	 */
	@Test
	public void checkValidTerm2() throws InvalidTermException {
		
		Term test = new Term(" (44.14) * ( ((4) + (7)) / (4.5)) ");
		
	}
	
	/**
	 * Tests a valid term using minus as Vorzeichen
	 * @throws InvalidTermException
	 */
	@Test
	public void checkValidTerm3() throws InvalidTermException {
		
		Term test = new Term(" -3 - -4 + -6 * -4 / -6");
		
	}
	
	/**
	 * Test creating a term that contains multiple .s
	 */
	@Test
	public void checkInvalidTerm1() {
		
		assertThrows(InvalidTermException.class, () -> new Term("3..5"));
		
	}
	
	/**
	 * Test a term that contains wrong parenthesis
	 */
	@Test
	public void checkInvalidTerm2() {
		
		assertThrows(InvalidTermException.class, () -> new Term("())"));
		
	}
	
	/**
	 * Test creating an invalid term that misses an operator
	 */
	@Test
	public void checkInvalidTerm3() {
		
		assertThrows(InvalidTermException.class, () -> new Term("4+5(3*4)"));
		
	}
	
	/**
	 * Test a valid complex expression
	 * @throws InvalidTermException 
	 */
	@Test
	public void checkResult1() throws InvalidTermException {
		
		assertEquals(new Term("(-4) * 3 + (592 - 49 / 1.4)").getResult(), "545");		
	}
	
	/**
	 * Test a valid expression containing minus Vorzeichens
	 * @throws InvalidTermException 
	 */
	@Test
	public void checkResult2() throws InvalidTermException {
		
		assertEquals(new Term("-1 * -4 - -4").getResult(), "8");
		
	}
	
	/**
	 * Test a valid expression
	 * @throws InvalidTermException 
	 */
	@Test
	public void checkResult3() throws InvalidTermException {
		
		assertEquals(new Term("95009 + 02342 - 062354 * 1 / 2").getResult(), "66174");
		
	}
	
}
