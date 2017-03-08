package org.eda1.prueba2_15;


public class Router implements Comparable<Router> {

	private String IP;
	private String ciudad;
	
	public Router(){
		IP = "";
		ciudad = "";
	}
	
	public Router (String IP, String ciudad){
		if(IP == null)
			throw new RuntimeException("...IP nula...");
		if(ciudad == null)
			throw new RuntimeException("...ciudad nula...");
		this.IP = IP;
		this.ciudad = ciudad.toUpperCase();
	}
	
	@Override
	public int compareTo(Router otro){
		if(ciudad.compareTo(otro.ciudad) < 0)
			return -1;
		if(ciudad.compareTo(otro.ciudad) > 0)
			return +1;
		return IP.compareTo(otro.IP);//si son iguales las ciudades compara por IP
	}
	
	@Override
	public boolean equals(Object otro){
		if(otro == null)//si es nulo
			return false;
		if(!(otro instanceof Router))//y si se puede intanciar como Router
			return false;
		Router nuevo = (Router) otro;
		return IP.equals(nuevo.IP);
		//se podria hacer una instruccion, pero CUIDADO
		//hay veces que el test me obliga a hacer los dos if
		//return IP.equals((Router)otro).IP;
	}
	
	@Override
	public String toString(){
		return "<" + IP + ", " + ciudad + ">";
	} 
}
