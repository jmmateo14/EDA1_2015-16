package org.eda1.practica02.ejercicio03;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	static Scanner entrada = new Scanner (System.in);
	
	public static void main(String[] args) {
		ArrayList<String> frase;
		String fichero = System.getProperty("user.dir") 
				+ File.separator +
				"src" + File.separator + 
				"org" + File.separator +
				"eda1" + File.separator + 
				"practica02" + File.separator +
				"ejercicio03" + File.separator + 
				"diccionario.txt";
		
		frase = LeerFrase();
		System.out.println(frase);
		CorrectorOrtografico corrector = new CorrectorOrtografico();
		corrector.cargarDiccionario(fichero);
		for (String palabra : frase) {
			if (corrector.containsPalabra(palabra))
				System.out.println("La palabra se encuentra en el diccionario");
			else {
				ArrayList<String> sugerencias = 
						corrector.listaSugerencias(4, palabra);
				System.out.println("Sugerencias que le podrian ser util:");
				System.out.println(sugerencias);
				int n;
				do {
					System.out.println("0-Pasar 1-Insertar");
					n = entrada.nextInt();
				}while (n<0 || n>1);
				if (n == 1)
					corrector.addPalabra(palabra);
			}
		}
		corrector.guardarDiccionario(fichero);
	}

	public static ArrayList<String> LeerFrase () {
		System.out.println("Introduzca una frase: ");
		String frase = entrada.nextLine();
		ArrayList<String> l = new ArrayList<String> ();
		Scanner sc = new Scanner (frase);
		while (sc.hasNext()) {
			String palabra = sc.next();
			l.add(palabra);
		}
		return l;
	}
}
