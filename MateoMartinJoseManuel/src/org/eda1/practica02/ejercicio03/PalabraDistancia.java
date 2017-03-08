package org.eda1.practica02.ejercicio03;

public class PalabraDistancia {

	private String palabra;
	private int distancia;
	
	public PalabraDistancia(String pal, int dis){
		palabra = pal;
		distancia = dis;
	}
	
	public PalabraDistancia(String pal){
		palabra = pal;
		distancia = 0;
	}
	
	public PalabraDistancia(){
		palabra = "";
		distancia = 0;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	public int compareTo (Object other){
		PalabraDistancia p = (PalabraDistancia) other;
		return palabra.compareTo(p.palabra);
	}
	
	public boolean equals (Object obj){
		PalabraDistancia p = (PalabraDistancia) obj;
		return palabra.equals(p.palabra);
	}
	
	public String toString(){
		return palabra + " " + distancia;
	}
	
}
