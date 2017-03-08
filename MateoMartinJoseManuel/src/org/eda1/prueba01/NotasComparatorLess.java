package org.eda1.prueba01;

import java.util.Comparator;


public class NotasComparatorLess implements Comparator<Alumno> 
{ 
    public int compare(Alumno al1, Alumno al2){
    	double nota1 = al1.calcularNotaMedia();
    	double nota2 = al2.calcularNotaMedia();
    	
    	if(nota1 < nota2)
    		return -1;
    	if(nota1 > nota2)
    		return 1;
    	return 0;
    } 
}