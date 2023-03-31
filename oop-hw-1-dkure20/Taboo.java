
/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.util.*;

public class Taboo<T> {
	HashMap<T,Set<T>> map;
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		map = new HashMap<>();
		for(int i=0; i<rules.size()-1; i++){
			T firstElem = rules.get(i);
			T secondElem =rules.get(i+1);
			if(firstElem == null || secondElem ==null)continue;
			if(!map.containsKey(firstElem)){
				map.put(firstElem,new HashSet<>());
			}
			map.get(firstElem).add(secondElem);
		}

	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		if(map.containsKey(elem))
			return map.get(elem);
		else
			return Collections.emptySet();
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		Iterator<T> it = list.iterator();
		T previous = null;
		while(it.hasNext()){
			T curr = it.next();
			if(previous!= null && map.containsKey(previous) && map.get(previous).contains(curr)){
				it.remove();
			}else{
				previous = curr;
			}

		}
	}
}
