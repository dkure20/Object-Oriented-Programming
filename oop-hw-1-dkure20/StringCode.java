import java.util.HashSet;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		if(str.isEmpty())return 0;
		int maxNum = 1;
		int ocr = 1;
		for(int i=0; i<str.length()-1;i++){
			if(str.charAt(i) == str.charAt(i+1)){
				ocr++;
				if(ocr > maxNum) maxNum = ocr;
			}else{
				ocr = 1;
			}
		}
		return maxNum;
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		if (str.isEmpty()) return "";
		String s = "";
		for (int i=0; i<str.length()-1; i++) {
			char ch = str.charAt(i);
			char nextCh = str.charAt(i+1);
			if (Character.isDigit(ch)){
				s += convertToString(Character.getNumericValue(ch), nextCh);
			} else {
				s += ch;
			}
		}
		char ch = str.charAt(str.length()-1);
		if (!Character.isDigit(ch)) s += ch;
		return s;
	}

	private static String convertToString(int parseInt, char nextCh) {
		String s = "";
		for(int i=0; i<parseInt; i++){
			s+=nextCh;
		}
		return s;
	}

	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {
		HashSet<String> Aset = new HashSet<>();
		HashSet<String> Bset = new HashSet<>();
		if(a.isEmpty() && b.isEmpty()){
			return false;
		}
		if(a.isEmpty() || b.isEmpty()){
			return false;
		}
		for(int i=0; i<a.length(); i++){
			if(i+len > a.length())break;
			String s = a.substring(i, i+len);
			Aset.add(s);
		}
		for(int i=0; i<b.length(); i++){
			if(i+len > b.length())break;
			String s = b.substring(i, i+len);
			Bset.add(s);
		}
		for (String substr : Aset) {
			if(Bset.contains(substr)) return true;
		}
		return false;
	}
}
