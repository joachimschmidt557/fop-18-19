package Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import Exceptions.InvalidTermException;

public class TermIO {

	public Term[] terms;
	
	/**
	 * Reads terms from the given file
	 * @param filePath The path of the file
	 * @return True if everything went fine, False when an error occurred
	 */
	public boolean readTermsFromFile(String filePath) {
		
		try {
			
			String[] linesInFile = Utils.readAllLinesFromFile(filePath);
			terms = new Term[linesInFile.length];
			for (int i = 0; i < linesInFile.length; i++)
				terms[i] = new Term(linesInFile[i]);
			return true;
			
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found: " + filePath);
			return false;
			
		} catch (UnsupportedEncodingException e) {
			
			System.out.println("Unsupported encoding: " + e.getMessage());
			return false;
			
		} catch (InvalidTermException e) {
			
			System.out.println("Invalid term: " + e.getTerm());
			return false;
			
		}
		
	}
	
	/**
	 * Writes the results of the terms contained in this class to a file
	 * @param filePath The path of the file
	 * @return True if everything went fine, False if some error occurred
	 */
	public boolean writeTermResultsToFile(String filePath) {
		
		try {
			
			String[] linesInFile = new String[terms.length];
			for (int i = 0; i < terms.length; i++)
				linesInFile[i] = terms[i].getResult();
			Utils.writeLinesToFile(filePath, linesInFile);
			return true;
			
		} catch (IOException e) {

			System.out.println("Error writing to file: " + e.getMessage());
			return false;
			
		}
		
	}
	
}
