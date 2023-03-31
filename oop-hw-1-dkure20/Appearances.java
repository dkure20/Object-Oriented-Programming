import java.util.*;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		HashMap<T,Integer> aMap = new HashMap<>();
		HashMap<T,Integer> bMap = new HashMap<>();
		findOccurences(aMap,a);
		findOccurences(bMap,b);
		int count = 0;
		for (T word: aMap.keySet()) {
			boolean bMapContains = bMap.containsKey(word);
			if(bMapContains && aMap.get(word).equals(bMap.get(word))){
				count++;
			}
		}
		return count;
	}

	private static <T> void findOccurences(HashMap<T, Integer> myMap, Collection<T> a) {
		Iterator<T> it = a.iterator();
		while(it.hasNext()){
			T input = it.next();
			if(myMap.containsKey(input)){
				int occ = myMap.get(input);
				myMap.put(input,occ+1);
			}else{
				myMap.put(input,1);
			}
		}
	}

}
