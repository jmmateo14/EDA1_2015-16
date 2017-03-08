package org.eda1.prueba01_2;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class GestionTrabajadorTestJUnit4 {
	String directorioEntrada = "";
	GestionTrabajador gestion = new GestionTrabajador();
	
	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir") + File.separator +
						    "src" + File.separator + 
						    "org" + File.separator +
						    "eda1" + File.separator + 
						    "prueba01_2" + File.separator;
	}

	@Test
	public void testTrabajador(){
		Trabajador trab1 = null, trab2 = null, trab3 = null;
		String nombre1 = "pepe Martin martin";
		String nombre2 = "joseFa Perez perez";
		String nombre3 = "emilio sanchez perez";
		String cadena = "";
		ArrayList<Double> horas1 = new ArrayList<Double>(30);
		ArrayList<Double> horas2 = new ArrayList<Double>(30);
		
		for (int i=0; i<30; i++){
			if (i < 15){
				horas1.add(new Double(8));
				horas2.add(null);
			}else{
				horas1.add(null);
				horas2.add(new Double(8));
			}
		}
		
		trab1 = new Trabajador(nombre1, horas1);
		trab2 = new Trabajador(nombre2, horas2);
		trab3 = new Trabajador(nombre3, null);

		assertTrue(trab1.horasMes()== 120.0);
		assertTrue(trab2.mediaHorasMes() == 4.0);
		assertTrue(trab3.horasMes() == .0);
		assertEquals(trab1.toString(), "PEPE MARTIN MARTIN <120.00, 4.00>");
		assertEquals(trab2.toString(), "JOSEFA PEREZ PEREZ <120.00, 4.00>");
		assertEquals(trab3.toString(), "EMILIO SANCHEZ PEREZ <0.00, 0.00>");
		
		//probamos toStringExtend
		cadena = "PEPE MARTIN MARTIN --> [8.0, 8.0, 8.0, 8.0, 8.0, 8.0, 8.0, 8.0, 8.0, 8.0, "
				+ "8.0, 8.0, 8.0, 8.0, 8.0, "
				+ "null, null, null, null, null, null, null, null, null, "
				+ "null, null, null, null, null, null]";
				
		assertEquals(trab1.toStringExtend(), cadena);
		cadena = "EMILIO SANCHEZ PEREZ --> []";	
		assertEquals(trab3.toStringExtend(), cadena);
		
		//probamos compareTo()
		
		assertTrue(trab1.compareTo(trab2)<0);
		assertTrue(trab3.compareTo(trab2)>0);
		assertTrue(trab3.compareTo(trab1)>0);
		assertTrue(trab1.compareTo(trab1)==0);
	}
	
	@Test
	public void cargarArchivo(){
		String cadena = "";
		gestion.cargarArchivo(directorioEntrada + "archivo");
		assertTrue(gestion.size()==4);
		gestion.add(new Trabajador("DUMP DUMP DUMP", null));
		assertTrue(gestion.size()==5);
		
		//Volvemos a cargar el archivo
		gestion.cargarArchivo(directorioEntrada + "archivo");
		assertTrue(gestion.size()==4);
				
		//Probamos el metodo toString()
		cadena = "[ADELAIDA IGLESIAS TORRES <59.50, 1.98>, EMILIA PEREZ GIMENEZ <24.00, 0.80>, "
				+ "JUAN SANCHEZ PEREZ <59.50, 1.98>, JUAN SANCHEZ SANCHEZ <57.00, 1.90>]";

		assertEquals(gestion.toString(),cadena);

		cadena = "ADELAIDA IGLESIAS TORRES --> [8.0, null, 7.0, null, 8.3, null, 9.2, null, 5.5, "
				+ "null, null, null, null, null, null, 10.5, null, null, null, 2.0, null, null, null, "
				+ "null, null, 9.0, null, null, null, null]";

		assertEquals(gestion.find("Adelaida iglesias torres").toStringExtend(),cadena);
		assertTrue(gestion.find("JUAN SANCHEZ GARCIA") == null);
		assertTrue(gestion.find("adelaida iglesias torres").horasMes() == gestion.find("juan sanchez perez").horasMes());
	}
	
	@Test
	public void testToArray(){
		gestion.cargarArchivo(directorioEntrada + "archivo");
		String cadena = "";
		ArrayList<Trabajador> array = gestion.toArray();
		
		cadena = "[ADELAIDA IGLESIAS TORRES <59.50, 1.98>, "
				+ "EMILIA PEREZ GIMENEZ <24.00, 0.80>, "
				+ "JUAN SANCHEZ PEREZ <59.50, 1.98>, "
				+ "JUAN SANCHEZ SANCHEZ <57.00, 1.90>]";

		assertEquals(array.toString(), cadena);
		
		assertEquals(gestion.toArrayWithOrder(new NombreComparatorLess()), array);
		Collections.reverse(array);
		assertEquals(gestion.toArrayWithOrder(new NombreComparatorGreater()),array);
	}
	
	@Test
	public void testToArrayWithOrder1(){
		gestion.cargarArchivo(directorioEntrada + "archivo");
		ArrayList<Trabajador> array1 = null, array2 = null;
		String cadena = "";
	
		array1 = gestion.toArrayWithOrder(new HorasComparatorLess());
		
		cadena = "[EMILIA PEREZ GIMENEZ <24.00, 0.80>, "
				+ "JUAN SANCHEZ SANCHEZ <57.00, 1.90>, "
				+ "ADELAIDA IGLESIAS TORRES <59.50, 1.98>, "
				+ "JUAN SANCHEZ PEREZ <59.50, 1.98>]";

		assertEquals(array1.toString(), cadena);
		array2 = gestion.toArrayWithOrder(new HorasComparatorGreater());

		cadena = "[JUAN SANCHEZ PEREZ <59.50, 1.98>, "
				+ "ADELAIDA IGLESIAS TORRES <59.50, 1.98>, "
				+ "JUAN SANCHEZ SANCHEZ <57.00, 1.90>, "
				+ "EMILIA PEREZ GIMENEZ <24.00, 0.80>]";

		assertEquals(array2.toString(), cadena);
		
		Collections.reverse(array2);
		assertEquals(array1,array2);
	}

	@Test
	public void testToArrayWithOrder2(){
		gestion.cargarArchivo(directorioEntrada + "archivo");
		ArrayList<Trabajador> array1 = null, array2 = null;		
		ArrayList<Double> nuevo = new ArrayList<Double>();
		String cadena = "";
		
		nuevo.add(59.50);
		gestion.add(new Trabajador("Zacarias z Z",nuevo));

		array1 = gestion.toArrayWithOrder(new HorasComparatorGreater());
		array2 = gestion.toArrayWithOrder(new HorasComparatorGreater2());
	
		assertFalse(array1.equals(array2));
	
		cadena = "[ZACARIAS Z Z <59.50, 1.98>, "
				+ "JUAN SANCHEZ PEREZ <59.50, 1.98>, "
				+ "ADELAIDA IGLESIAS TORRES <59.50, 1.98>, "
				+ "JUAN SANCHEZ SANCHEZ <57.00, 1.90>, "
				+ "EMILIA PEREZ GIMENEZ <24.00, 0.80>]";

		assertEquals(array1.toString(), cadena);
		
		cadena = "[ADELAIDA IGLESIAS TORRES <59.50, 1.98>, "
				+ "JUAN SANCHEZ PEREZ <59.50, 1.98>, "
				+ "ZACARIAS Z Z <59.50, 1.98>, "
				+ "JUAN SANCHEZ SANCHEZ <57.00, 1.90>, "
				+ "EMILIA PEREZ GIMENEZ <24.00, 0.80>]";

		assertEquals(array2.toString(), cadena);
	}
}