package org.eda1.prueba01;

import java.util.Comparator;


public class NombreComparatorLess implements Comparator<Alumno>{ 
    public int compare(Alumno al1, Alumno al2){ 
    	return al1.nombreCompleto().compareTo(al2.nombreCompleto());
    } 
}