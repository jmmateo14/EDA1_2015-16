package org.eda1.prueba01;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GestionAlumnosTestJUnit4 {
	String directorioEntrada = "";
	GestionAlumnos curso1415 = new GestionAlumnos();
	
	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir") + File.separator +
						    "src" + File.separator + 
						    "org" + File.separator +
						    "eda1" + File.separator + 
						    "prueba01" + File.separator;
	}

	@Test
	public void testToStringAlumno(){
		Alumno al1 = null, al2 = null, al3 = null, al4 = null;
		ArrayList<Double> notas = new ArrayList<Double>();
		
		notas.add(3.0);
		notas.add(2.0);
		notas.add(5.553434);
		notas.add(2.65);
		
		al1 = new Alumno("ZZZ", "ZZZ", "ZZZ", notas);
		assertEquals(al1.toString(), "ZZZ ZZZ ZZZ (3.30)");
		
		notas.clear();
		assertEquals(al1.toString(), "ZZZ ZZZ ZZZ (3.30)");
		
		
		notas.add(null);
		notas.add(null);
		notas.add(3.5);
		notas.add(null);
		al2 = new Alumno("AAA", "GGG", "ZZZ", notas);
		assertEquals(al2.toString(), "GGG ZZZ AAA (0.88)");
		
		al3 = new Alumno("Javier", "MARTINEZ", "RODRIGUEZ");
		assertEquals(al3.toString(), "MARTINEZ RODRIGUEZ JAVIER (0.00)");
		
		al4 = new Alumno("Javier", "Martinez", "Torrente", null);
		assertEquals(al4.toString(), "MARTINEZ TORRENTE JAVIER (0.00)");
		
		assertTrue(al1.compareTo(al2)==1);
		try{
			al2.compareTo(null);
		}catch (Exception e){
			assertEquals(e.getMessage(),"...comparando con nulo...");
		}
		al1 = al2 = al3 = al4 = null;
	}

	@Test
	public void testcargaArchivo(){
		curso1415.cargarDatos(directorioEntrada + "alumnos.txt");
		assertTrue(curso1415.size() == 12);
	}
	
	
	@Test
	public void testToStringGestionAlumnos(){
		String resultado = "";
		curso1415.cargarDatos(directorioEntrada + "alumnos.txt");
		resultado ="[CUERA RODRIGUEZ JESUS (4.75), "
			        + "GOMEZ GOMEZ EULOGIO (7.25), "
			        + "LOPEZ SEGURA JUAN (5.00), " 
			        + "MARTIN PEREZ PABLO (2.25), "
			        + "MARTIN PEREZ PAULA (4.00), "
			        + "PEREZ GARCIA AMALIA (0.00), "
			        + "PEREZ GARCIA ZACARIAS (0.28), "
			        + "SABINA LOPEZ JOAQUINA (10.00), " 
			        + "SANCHEZ SANCHEZ JOAQUIN (1.53), "
			        + "SANCHEZ SANCHEZ PEDRO (5.00), " 
			        + "SANCHEZ SANCHEZ SABINA (3.25), "
			        + "SMITH  JOHN (5.00)]"; 
		assertEquals(curso1415.toString(), resultado);
	}
	
	@Test
	public void consultarExpediente(){
		String resultado = "";
		curso1415.cargarDatos(directorioEntrada + "alumnos.txt");

		resultado = curso1415.consultarExpediente("LOPEZ", "SEGURA", "JUAN");
		assertEquals("JUAN LOPEZ SEGURA --> [2.0, 3.0, 5.0, 10.0]", resultado);
		
		resultado = curso1415.consultarExpediente("PEREZ", "GARCIA", "AMALIA");
		assertEquals("AMALIA PEREZ GARCIA --> [No presentado, No presentado, No presentado, No presentado]", resultado);
		
		resultado = curso1415.consultarExpediente("SABINA", "Lopez", "Joaquina");
		assertEquals("JOAQUINA SABINA LOPEZ --> [10.0, 10.0, 10.0, 10.0]", resultado);
		
		resultado = curso1415.consultarExpediente("PEREZ", "GARCIA", "zacarias");
		assertEquals("ZACARIAS PEREZ GARCIA --> [1.1, No presentado, No presentado, No presentado]", resultado);
		
		
		resultado = curso1415.consultarExpediente("PEREZ", "GARCIA", "AMALIO");
		assertEquals("Este alumno/a no existe", resultado);
	}
	
	@Test
	public void ListadoMejoresExpedientes(){
		String resultado = "";

		curso1415.cargarDatos(directorioEntrada + "alumnos.txt");

		//Los 3 mejores expedientes son:
		
		resultado = "[JOAQUINA SABINA LOPEZ, EULOGIO GOMEZ GOMEZ, PEDRO SANCHEZ SANCHEZ]";		
		assertEquals(resultado, curso1415.extraerListado(3, new NotasComparatorGreater()));


		//Los 5 mejores expedientes son:

		resultado = "[JOAQUINA SABINA LOPEZ, EULOGIO GOMEZ GOMEZ, PEDRO SANCHEZ SANCHEZ, JUAN LOPEZ SEGURA, JOHN SMITH]";		
		assertEquals(resultado, curso1415.extraerListado(5, new NotasComparatorGreater()));
	}

	@Test
	public void ListadoPeoresExpedientes(){
		String resultado = "";

		curso1415.cargarDatos(directorioEntrada + "alumnos.txt");

		//Los 3 peores expedientes son:
		
		resultado = "[AMALIA PEREZ GARCIA, ZACARIAS PEREZ GARCIA, JOAQUIN SANCHEZ SANCHEZ]";
		assertEquals(resultado, curso1415.extraerListado(3, new NotasComparatorLess()));


		//Los 5 peores expedientes son:

		resultado = "[AMALIA PEREZ GARCIA, ZACARIAS PEREZ GARCIA, JOAQUIN SANCHEZ SANCHEZ, PABLO MARTIN PEREZ, SABINA SANCHEZ SANCHEZ]";
		assertEquals(resultado, curso1415.extraerListado(5, new NotasComparatorLess()));
	}

	@Test
	public void ListadoOrdenadoPorNombre(){
		String resultado = "";

		curso1415.cargarDatos(directorioEntrada + "alumnos.txt");
		
		resultado = "[AMALIA PEREZ GARCIA, "
				  + "EULOGIO GOMEZ GOMEZ, "
				  + "JESUS CUERA RODRIGUEZ, " 
				  + "JOAQUIN SANCHEZ SANCHEZ, "
				  + "JOAQUINA SABINA LOPEZ, "
				  + "JOHN SMITH, "
				  + "JUAN LOPEZ SEGURA, "
				  + "PABLO MARTIN PEREZ, "
				  + "PAULA MARTIN PEREZ, "
				  + "PEDRO SANCHEZ SANCHEZ, "
				  + "SABINA SANCHEZ SANCHEZ, "
				  + "ZACARIAS PEREZ GARCIA]";

		assertEquals(resultado, curso1415.extraerListado(curso1415.size(), new NombreComparatorLess()));
	}
}