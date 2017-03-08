package org.eda1.actividad01.serializacionED;

 import java.io.Serializable;
 
 public class CiudadBarrios implements Serializable{
	 public String ciudad;
	 public Double latitud;
	 public Double longitud;
	 LinkedList<String> barrios  = new LinkedList<String>();
	 
	 public CiudadBarrios(String ciudad, Double latitud, Double longitud){
		 this.ciudad = ciudad;
		 this.latitud = latitud;
		 this.longitud = longitud;
	 }
	 
	 public boolean addBarrio(String barrio){
		 if(barrios.contains(barrio))
			 return false;
		 barrios.add(barrio);
		 return true;
	 }
	 
	 public LinkedList<String> getBarrios(){
		 return barrios;
	 }
	 
	 public String toString(){
		 String cadena = "";
		 
		 cadena = ciudad + ", " + latitud + ", " +  longitud + ", {";
		 for (int i = 0; i < barrios.size(); i++) {
			if(i < barrios.size()-1)
				cadena = cadena + barrios.get(i) + ", ";
			else
				cadena = cadena + barrios.get(i);
		}
		 cadena = cadena + "}";
		 return cadena;
	 }
 }