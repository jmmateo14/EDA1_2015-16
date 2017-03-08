package org.eda1.prueba2_15;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class GestionRoutersTestJUnit4 {
	String directorioEntrada = "";
	String graphFile = "";
	
	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir") + File.separator +
						    "src" + File.separator + 
						    "org" + File.separator +
						    "eda1" + File.separator + 
						    "prueba2_15" + File.separator;

	}

	@Test
	public void testRouter(){
		Router r1 = null, r2 = null, r3 = null;
		
		r1 = new Router("000.000.000.000", "Almeria");
		assertEquals(r1.toString(), "<000.000.000.000, ALMERIA>");
		
		r2 = new Router("111.111.111.111", "Almeria");
		assertEquals(r2.toString(), "<111.111.111.111, ALMERIA>");
		
		try{
			r3 = new Router(null, "GRANADA");
		}catch (Exception e){
			assertEquals(e.getMessage(), "...IP nula...");
		}
		
		try{
			r3 = new Router("1.1.1.1", null);
		}catch (Exception e){
			assertEquals(e.getMessage(), "...ciudad nula...");
		}
		
		r3 = new Router ("333.3.2.3", "Sevilla");
		assertEquals(r3.toString(), "<333.3.2.3, SEVILLA>");
		assertTrue(r1.compareTo(r2) < 0);
		assertTrue(r2.compareTo(r1) > 0);
		assertTrue(r1.compareTo(r1) == 0);
		assertFalse(r1.equals(r3));
		assertTrue(r1.compareTo(r3) < 0);
		
		r1 = r2 = r3 = null;
	}

	@Test
	public void testReadNetwork() throws FileNotFoundException{
		graphFile = directorioEntrada + "listado.txt";
		Router r1 = null, r2 = null;
		NetworkRouters<Router> net = new NetworkRouters<Router>();
		try{
			net.readNetwork(graphFile + "..."); 
		}catch (Exception e){
			assertTrue(e.getMessage().equals("...ERROR AL CARGAR ARCHIVO..."));
		}
		
		net.readNetwork(graphFile);
		assertTrue(net.numberOfEdges() == 9);
		assertTrue(net.numberOfVertices() == 5);
		assertEquals(net.vertexSet().toString(), "[<1.1.1.1, ALMERIA>, <5.5.5.5, ALMERIA>, <2.2.2.2, GRANADA>, <2.2.2.4, GRANADA>, <2.2.2.5, SEVILLA>]");
		assertEquals(net.getVertexToIndex().toString(), "{<1.1.1.1, ALMERIA>=0, <5.5.5.5, ALMERIA>=1, <2.2.2.2, GRANADA>=2, <2.2.2.4, GRANADA>=3, <2.2.2.5, SEVILLA>=4}");
		assertTrue(net.getVertexToIndex().size() == net.numberOfVertices());
		
		r1 = new Router("5.5.5.5", "ALMERIA");
		r2 = new Router("1.1.8.1", "GRANADA");
		assertFalse(net.isAdjacent(r1, r2));
		
		r2 = new Router("2.2.2.4", "GRANADA");
		assertTrue(net.isAdjacent(r1, r2));
		
		assertEquals(String.format(Locale.US,  "%.2f",net.getWeight(r1,r2)),"21.90");
	}

	@Test
	public void testFloyd() throws FileNotFoundException{
		graphFile = directorioEntrada + "listado.txt";
		String cadena = null;
		ManageFloyd<Router> manageFloyd = null;
		NetworkRouters<Router> net = new NetworkRouters<Router>();
		Object[] rFloyd = null;
		double[][] matrixD = null;
		int[][] matrixA = null;
		net.readNetwork(graphFile);
	
		rFloyd = net.floyd();
		matrixD = (double[][]) rFloyd[0];
		matrixA = (int[][]) rFloyd[1];
		assertTrue(matrixD.length == 5);
		assertTrue(matrixA.length == 5);
		manageFloyd = new ManageFloyd<Router>(matrixD, matrixA, net.getVertexToIndex());
		cadena = "[Camino #1: <1.1.1.1, ALMERIA> --> <5.5.5.5, ALMERIA> (32.40), " 
		        + "Camino #2: <1.1.1.1, ALMERIA> --> <5.5.5.5, ALMERIA> --> <2.2.2.4, GRANADA> (54.30), "
		        + "Camino #3: <1.1.1.1, ALMERIA> --> <5.5.5.5, ALMERIA> --> <2.2.2.5, SEVILLA> (58.80), "
		        + "Camino #4: <5.5.5.5, ALMERIA> --> <2.2.2.4, GRANADA> --> <1.1.1.1, ALMERIA> (96.30), "
		        + "Camino #5: <5.5.5.5, ALMERIA> --> <2.2.2.4, GRANADA> (21.90), "
		        + "Camino #6: <5.5.5.5, ALMERIA> --> <2.2.2.5, SEVILLA> (26.40), "
		        + "Camino #7: <2.2.2.2, GRANADA> --> <1.1.1.1, ALMERIA> (67.50), "
		        + "Camino #8: <2.2.2.2, GRANADA> --> <5.5.5.5, ALMERIA> (48.00), "
		        + "Camino #9: <2.2.2.2, GRANADA> --> <5.5.5.5, ALMERIA> --> <2.2.2.4, GRANADA> (69.90), "
		        + "Camino #10: <2.2.2.2, GRANADA> --> <2.2.2.5, SEVILLA> (56.70), "
		        + "Camino #11: <2.2.2.4, GRANADA> --> <1.1.1.1, ALMERIA> (74.40), "
		        + "Camino #12: <2.2.2.4, GRANADA> --> <1.1.1.1, ALMERIA> --> <5.5.5.5, ALMERIA> (106.80), "
		        + "Camino #13: <2.2.2.4, GRANADA> --> <2.2.2.5, SEVILLA> (54.80), "
		        + "Camino #14: <2.2.2.5, SEVILLA> --> <2.2.2.4, GRANADA> --> <1.1.1.1, ALMERIA> (107.30), "
		        + "Camino #15: <2.2.2.5, SEVILLA> --> <2.2.2.4, GRANADA> --> <1.1.1.1, ALMERIA> --> <5.5.5.5, ALMERIA> (139.70), "
		        + "Camino #16: <2.2.2.5, SEVILLA> --> <2.2.2.4, GRANADA> (32.90)]";

		assertEquals(manageFloyd.toString(),cadena);

		cadena = "Camino #1: <1.1.1.1, ALMERIA> --> <5.5.5.5, ALMERIA> --> <2.2.2.4, GRANADA> (54.30)\n"
   		       + "Camino #6: <2.2.2.4, GRANADA> --> <2.2.2.5, SEVILLA> (54.80)\n"
		       + "Camino #5: <2.2.2.2, GRANADA> --> <2.2.2.5, SEVILLA> (56.70)\n"
		       + "Camino #2: <1.1.1.1, ALMERIA> --> <5.5.5.5, ALMERIA> --> <2.2.2.5, SEVILLA> (58.80)\n"
	   	       + "Camino #3: <2.2.2.2, GRANADA> --> <1.1.1.1, ALMERIA> (67.50)\n"
		       + "Camino #4: <2.2.2.2, GRANADA> --> <5.5.5.5, ALMERIA> --> <2.2.2.4, GRANADA> (69.90)\n";

		assertEquals(manageFloyd.filterPathsByDistance(50.0, 70.0, "<"), cadena);
		
		cadena = "Camino #4: <2.2.2.2, GRANADA> --> <5.5.5.5, ALMERIA> --> <2.2.2.4, GRANADA> (69.90)\n"
		       + "Camino #3: <2.2.2.2, GRANADA> --> <1.1.1.1, ALMERIA> (67.50)\n"
		       + "Camino #2: <1.1.1.1, ALMERIA> --> <5.5.5.5, ALMERIA> --> <2.2.2.5, SEVILLA> (58.80)\n"
		       + "Camino #5: <2.2.2.2, GRANADA> --> <2.2.2.5, SEVILLA> (56.70)\n"
		       + "Camino #6: <2.2.2.4, GRANADA> --> <2.2.2.5, SEVILLA> (54.80)\n"
   		       + "Camino #1: <1.1.1.1, ALMERIA> --> <5.5.5.5, ALMERIA> --> <2.2.2.4, GRANADA> (54.30)\n";

		assertEquals(manageFloyd.filterPathsByDistance(50.0, 70.0, ">"), cadena);
		assertEquals(manageFloyd.filterPathsByDistance(0, 200, "no se el orden"),null);
	}
}