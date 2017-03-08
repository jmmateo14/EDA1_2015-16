package org.eda1.practica02.ejercicio03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

import org.eda1.estructurasdedatos.AVLTree;

public class CorrectorOrtografico {

	private AVLTree<String> treePalabras;
	
	public CorrectorOrtografico(){
		treePalabras = new AVLTree<String>();
	}
	
	public CorrectorOrtografico(AVLTree<String> treePalabras){
		this.treePalabras  = treePalabras;
	}
	
	public AVLTree<String> cargarDiccionario (String archivo){
		treePalabras.clear();
		try{
			BufferedReader f = new BufferedReader(new FileReader(archivo));
			String palabra = f.readLine();
			while(palabra != null){
				treePalabras.add(palabra);
				palabra = f.readLine();
			}
			f.close();
		}
		catch(IOException e){	
		}
		return treePalabras;
	}
	
	public void guardarDiccionario (String archivo){
		try{
			PrintWriter f = new PrintWriter(new FileWriter(archivo));
			for(String palabra : treePalabras){
				f.write(palabra + "\n");
			}
			f.close();
		}
		catch(IOException e){	
		}
	}
	
	private static int minimum(int a, int b, int c) {
        if(a<=b && a<=c)
        {
            return a;
        } 
        if(b<=a && b<=c)
        {
            return b;
        }
        return c;
    }

    public static int computeLevenshteinDistance(String str1, String str2) {
        return computeLevenshteinDistance(str1.toCharArray(),
                                          str2.toCharArray());
    }

    private static int computeLevenshteinDistance(char [] str1, char [] str2) {
        int [][]distance = new int[str1.length+1][str2.length+1];

        for(int i=0;i<=str1.length;i++)
        {
                distance[i][0]=i;
        }
        for(int j=0;j<=str2.length;j++)
        {
                distance[0][j]=j;
        }
        for(int i=1;i<=str1.length;i++)
        {
            for(int j=1;j<=str2.length;j++)
            { 
                  distance[i][j]= minimum(distance[i-1][j]+1,
                                        distance[i][j-1]+1,
                                        distance[i-1][j-1]+
                                        ((str1[i-1]==str2[j-1])?0:1));
            }
        }
        return distance[str1.length][str2.length];
        
    }
    
    public ArrayList<String> listaSugerencias (int n, String s){
    	DistanceComparator comparator = new DistanceComparator();
    	PriorityQueue<PalabraDistancia> cola = new PriorityQueue<PalabraDistancia>(comparator);
    	for(String palabra : treePalabras){
    		int distancia = computeLevenshteinDistance(s, palabra);
    		PalabraDistancia pd = new PalabraDistancia(palabra, distancia);
    		cola.add(pd);
    	}
    	ArrayList<String> l = new ArrayList<String>();
    	System.out.println(cola.size());
    	for(int i = 0; i < n; i++) {
    		l.add(cola.poll().getPalabra());
    	}
    	System.out.println(l);
    	return l;
    }
    
    public void addPalabra(String palabra){
    	if(!treePalabras.contains(palabra))
    		treePalabras.add(palabra);
    }
    
    public boolean containsPalabra(String palabra){
    	return treePalabras.contains(palabra);
    }
    
    public int size(){
    	return treePalabras.size();
    }
    
    public boolean add(String palabra){
    	return treePalabras.add(palabra);
    }
    
    public boolean find (String palabra){
    	String buscada = treePalabras.find(palabra);
    	if(buscada != null)
    		return true;
    	return false;
    }
}
