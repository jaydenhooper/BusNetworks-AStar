package comp261.assig2;

import java.util.*;

/**
 * This is an implementation of a trie, used for the search box.
 */

public class Trie {
	Stop data; //the stop at the end of the node
	Map<Character, Trie> children = new HashMap<>();

	//constructor for first making the trie
	public Trie() {

	}
	//constructor for making another node in the trie
	public Trie(Stop stop) {
		data = stop;
	}

	/**
	 * Adds a given stop to the Trie.
	 */
	public void add(String word,Stop stop) {
		//if this is a leaf node, then add the stop to data.
		if (word.length() == 0) {
			this.data = stop;
			return;
		}
		//if not a leaf node, make a char from the word to use as a new node.
		char c = word.charAt(0);
		//check to see if we got this char node yet
		Trie child = children.get(c);
		if (child == null) {
			//if not make a new node with the char
			child = new Trie(null);
			children.put(c, child);
		}
		//recersively call add() unto word is only 1 char long
		child.add(word.substring(1), stop);
	}

	/*
	*add all children to the Stop List
	*/
	private List<Stop> getAllChildren(List<Stop> list) {
		if (data != null) {
			list.add(data);
		}

		for (Trie child : children.values()) {
			child.getAllChildren(list);
		}
		return list;
	}

	public List<Stop> get(String word) {
		if (word.length() == 0) {
			List<Stop> result = new ArrayList<Stop>();
			result = getAllChildren(result);
			return result;
		}
		char c = word.charAt(0);
		Trie child = children.get(c);
		if (child == null) {
			return new ArrayList<Stop>();
		}

		return child.get(word.substring(1));
	}
}