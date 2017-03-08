package org.eda1.practica03.ejercicio03;

import java.util.regex.*;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.TreeMap;

public class Concordancia {
	
	private Pattern identifierPattern;
	private Matcher matcher;

	public Concordancia(String expresion) {
		this.identifierPattern = Pattern.compile(expresion);
		this.matcher = null;
	}
	
	public String concordanceV1(String cadena){
		Scanner fin = new Scanner(cadena);
		TreeMap<String, TreeSet<Integer>> concordanceMap = null;
		String inputLine = null;
		String identifier = null;
		int lineNumber = 0;

		concordanceMap = new TreeMap<String, TreeSet<Integer>>();
		
		while(fin.hasNext()) {
			inputLine = fin.nextLine();
			lineNumber++;

			matcher = identifierPattern.matcher(inputLine);
			
			while (matcher.find()) {
				identifier = inputLine.substring(matcher.start(), matcher.end());
				//........ lo tengo que meter en el mapa
				if(concordanceMap.containsKey(identifier))
					concordanceMap.get(identifier).add(lineNumber);
				else{//si no se encuentra el identificador
					TreeSet<Integer> set = new TreeSet<Integer>();
					set.add(lineNumber); //lo meto con el lineNumber
					concordanceMap.put(identifier, set);
				}
				
			}
		}
		fin.close();
		return formatOutputMapSet(concordanceMap);
	
	}
	
	private String formatOutputMapSet(TreeMap<String, TreeSet<Integer>> mapa){
		//Similar a un toString() personalizado...
		String cadena = "";
		for(Entry<String, TreeSet<Integer>> e : mapa.entrySet()){
			cadena = cadena + e.getKey() + "\t" + e.getValue().size() + ":";
			for(Integer n : e.getValue()){
				cadena = cadena + "\t" + n;
			}
			cadena = cadena + "\n";
		}
		return cadena;
	}
	
	
	public String concordanceV2(String cadena){
		Scanner fin = new Scanner(cadena);
		TreeMap<String, TreeMap<Integer, Integer>> concordanceMap = null;
		String inputLine = null;
		String identifier = null;
		int lineNumber = 0;

		concordanceMap = new TreeMap<String, TreeMap<Integer, Integer>>();
		
		while(fin.hasNext()) {
			inputLine = fin.nextLine();
			lineNumber++;

			matcher = identifierPattern.matcher(inputLine);
			
			while (matcher.find()) {
				identifier = inputLine.substring(matcher.start(), matcher.end());
				//........ lo tengo que meter en el mapa
				if(concordanceMap.containsKey(identifier)){
					if(concordanceMap.get(identifier).containsKey(lineNumber)){
						int n = concordanceMap.get(identifier).get(lineNumber);
						concordanceMap.get(identifier).put(lineNumber, n + 1);
					}
					else{
						//añadimos la linea si ya tenemos el identificador
						concordanceMap.get(identifier).put(lineNumber, 1);
					}
					
				}
				else{//si no se encuentra el identificador
					TreeMap<Integer, Integer> mapa = new TreeMap<Integer, Integer>();
					mapa.put(lineNumber, 1);
					concordanceMap.put(identifier, mapa);
				}
			}
		}
		fin.close();
		return formatOutputMapMap(concordanceMap);
	
	}

	private String formatOutputMapMap(TreeMap<String, TreeMap<Integer, Integer>> mapa){
		//Similar a un toString() personalizado...
				String cadena = "";
				for(Entry<String, TreeMap<Integer, Integer>> e : mapa.entrySet()){
					cadena = cadena + e.getKey() + "\t";
					int suma = 0;
					for(Entry<Integer, Integer> e2 : e.getValue().entrySet()){
						suma = suma + e2.getValue();
					}
					cadena = cadena + suma + ":";
					for(Entry<Integer, Integer> e2 : e.getValue().entrySet()){
						cadena = cadena + "\t" + e2.getKey() + "(" + e2.getValue() + ")";
					}
					cadena = cadena + "\n";
				}
				return cadena;
	}
}