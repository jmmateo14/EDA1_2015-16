package org.eda1.actividad04.ejercicio02;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Thesaurus
{
	protected TreeMap<String, TreeSet<String>> thesaurusMap;


	/**
 	 *  Initializes this Thesaurus object.
 	 *
 	 */
	public Thesaurus()
	{
		thesaurusMap  = new TreeMap<String, TreeSet<String>>();
	} // default constructor


	/**
 	 *  Adds a specified line of synonyms to this Thesaurus object.
 	 *  The worstTime(n) is O(log n).
 	 *
 	 *  @param line - the specified line of synonyms to be added to this
 	 *                Thesaurus object.
 	 *
 	 */
	public void add(String line) {
    
		String [] palabra = line.split(" ");
		for(int i = 1; i < palabra.length; i++){
			add(palabra[0], palabra[i]);
		}
	} // method add

	public void add(String word, String synonym) {
		if(thesaurusMap.containsKey(word))
			thesaurusMap.get(word).add(synonym);
		else{
			TreeSet<String> set = new TreeSet<String>();
			set.add(synonym);
			thesaurusMap.put(word, set);
		}
	} // method add

	public TreeSet<String> remove(String word) {
    if( ! thesaurusMap.containsKey(word))
    	return null;
    return thesaurusMap.remove(word);
	}

	public boolean remove(String word, String synonym) {
		if( ! thesaurusMap.containsKey(word))
			return false;
		if( ! thesaurusMap.get(word).contains(synonym))
			return false;
		thesaurusMap.get(word).remove(synonym);
		return true;
	}

	public TreeSet<String> update(String word, LinkedList<String> synonyms) {
		//si no esta la palabra no puedo actualizarla
		if( ! thesaurusMap.containsKey(word))
	    	return null;
		
		TreeSet<String> set = new TreeSet<String>(thesaurusMap.get(word));
		thesaurusMap.get(word).clear();//borro lo que tenia
		thesaurusMap.get(word).addAll(synonyms);//añado la nueva palabra
		return set;
	}

	public int size() {
		return thesaurusMap.size();
	}

	/**
 	 *  Finds the TreeSet of synonyms of a specified word in this Thesaurus.
 	 *  The worstTime(n) is O(log n).
 	 *
 	 *  @param word - the specified word, whose synonyms are to be
 	 *                 returned.
 	 *
 	 *  @return the LinkedList of synonyms of word.
 	 *
 	 */
	public TreeSet<String> getSynonymous(String word) {
		if( ! thesaurusMap.containsKey(word))
			return null;
		return thesaurusMap.get(word);
	} // method getSynonyms

	public int size(String word) {
		if( ! thesaurusMap.containsKey(word))
			return 0;
		return thesaurusMap.get(word).size();
	}

	public boolean isSynonymousOf(String word, String synonym) {
		if( ! thesaurusMap.containsKey(word))
			return false;
		return thesaurusMap.get(word).contains(synonym);
	}

	public boolean isSynonymous(String synonym) {
		for(Entry<String, TreeSet<String>> e : thesaurusMap.entrySet()){
			if(e.getValue().contains(synonym))
				return true;
		}
		return false;
	}

	public boolean hasSynonymous(String word) {
		if( ! thesaurusMap.containsKey(word))
			return false;
		if (thesaurusMap.get(word).size() == 0)
			return false;
		return true;
	}
	
	public String showThesaurus() {
		return null;
	}

} // class Thesaurus
