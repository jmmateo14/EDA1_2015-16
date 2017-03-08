package org.eda1.prueba2_15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.eda1.estructurasdedatos.Collection;
import org.eda1.utilidades.Greater;
import org.eda1.utilidades.Less;

public class ManageFloyd <Vertex> {
	final double INFINITY = Double.MAX_VALUE;
	private double [][] matrixD;
	private int[][]matrixA;
	private TreeMap<Vertex,Integer> vertexToIndex;
	
	public ManageFloyd(double[][]matrixD, int[][]matrixA, TreeMap<Vertex, Integer> v2i){
		this.matrixD = matrixD;
		this.matrixA = matrixA;
		this.vertexToIndex = v2i;
	}
	
 	public Vertex getVertexFromIndex(int index){
  		Vertex v = null;
  		for (Map.Entry<Vertex,Integer> e : vertexToIndex.entrySet()){
  			if (e.getValue() != index) continue;
  			v = e.getKey();
  			break;
  		}
  		return v;
  	}
	
 	@Override
	public String toString(){
 		//Método toString(). Formato --> véase el test... 
 		ArrayList<String> caminos = getPaths();
 		return caminos.toString();
 	}
	
	public ArrayList<String> getPaths(){
		ArrayList<String> resultado = new ArrayList<String>();
		int numPaths = 1;
		for (int i=0; i<this.vertexToIndex.size(); i++){
			for (int j=0; j<this.vertexToIndex.size(); j++){
				if (i==j) continue;
				if (matrixD[i][j] == INFINITY) continue;
				resultado.add("Camino #" + (numPaths++) + ": " + findPath(i,j)  + " (" + String.format(Locale.US, "%.2f",matrixD[i][j]) + ")");
			}
		}
		
		return resultado;
	}
	
	private String findPath(int vertexI, int vertexJ){
		ArrayList<Vertex> camino = new ArrayList<Vertex>();
		String resultado = "";
		findPathAux(vertexI, vertexJ, camino);
		if (camino.isEmpty()) return "";
		resultado = camino.get(0).toString();
		for (int i=1; i<camino.size(); i++){
			if (camino.get(i) != camino.get(i-1)){
				resultado += " --> " + camino.get(i).toString();
			}
		}
		return resultado;
	}
	
	private void findPathAux(int vertexI, int vertexJ, ArrayList<Vertex> camino){
		int vertexK = matrixA[vertexI][vertexJ];
		if (vertexK == -1){ 
			camino.add(this.getVertexFromIndex(vertexI));
			camino.add(this.getVertexFromIndex(vertexJ));
		}else{
			findPathAux(vertexI, vertexK, camino);
			findPathAux(vertexK, vertexJ, camino);
		}
	}	
	
	public String filterPathsByDistance(double distMin, double distMax, String orden){
	
	
//		Devuelve un String que contiene los caminos (ordenados por coste) cuyo coste está comprendido en el intervalo [distMin, distMax]
//		El formato --> véase test
//		El string orden indica el orden:
//			< --> orden ascendente (de menor a mayor)
//			> --> orden ascencente (de mayor a menor)
//			cualquier otro valor --> devolverá null
		
		if( ! orden.equals("<") && ! orden.equals(">"))
			return null;
		TreeMap<Double, String>caminos = new TreeMap<Double, String>();//clave double y como contenido el String
		int numPaths = 1;
		for (int i=0; i<this.vertexToIndex.size(); i++){
			for (int j=0; j<this.vertexToIndex.size(); j++){
				if (i==j) continue;
				if (matrixD[i][j] == INFINITY) continue;
				if (matrixD[i][j] >= distMin && matrixD[i][j] <= distMax){
				caminos.put(matrixD[i][j], "Camino #" + (numPaths++) + ": " + findPath(i,j)  + " (" + String.format(Locale.US, "%.2f",matrixD[i][j]) + ")");
				}
			}
		}
		ArrayList<String>caminos2 = new ArrayList<String>(caminos.values());
		if(orden.equals(">"))
			Collections.reverse(caminos2);
		String cadena = "";
		for(String s : caminos2){
			cadena = cadena + s + "\n";
		}
		return cadena;
	}
}

