package Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;

import Math.StringMath;

public class Utils {
	
	public static StringMath math;
	
	/**
	 * @param filePath
	 * @return Reads in a file line by line and returns a string array of those lines
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static String[] readAllLinesFromFile(String filePath) throws FileNotFoundException, UnsupportedEncodingException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
		return bufferedReader.lines().toArray(size -> new String[size]);
	}
	
	/**
	 * Writes each string of the array concatenated with "\n" into the file at the given file path
	 * @param filePath
	 * @param lines
	 * @throws IOException
	 */
	public static void writeLinesToFile(String filePath, String[] lines) throws IOException {
		BufferedWriter fw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
		
		fw.write(lines[0]);
		
		for(int i = 1; i < lines.length; i++) {
			fw.write("\n");
			fw.write(lines[i]);
		}
		fw.close();
	}
	
	/**
	 * @param d
	 * @return d converted into a string
	 */
	public static String doubleToString(double d) {
		String s = String.format(Locale.ROOT, "%.10f", d);
		String split[] = s.split(Pattern.quote("."));
		String beforeDecimalSeperator = split[0];
		String afterDecimalSeperator = split[1];
		char[] after = afterDecimalSeperator.toCharArray();
		for (int i = after.length - 1; i > -1; i--) {
			char c = after[i];
			if (c != '0') {
				return beforeDecimalSeperator + "." + afterDecimalSeperator.substring(0, i + 1);
			}
		}
		return beforeDecimalSeperator;
	}
	
	
	
}
