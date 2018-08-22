import static org.junit.Assert.*;

import org.junit.Test;

public class PaqueteHandlerTest {

	PaqueteHandler manejadorP = new PaqueteHandler();
	
	@Test
	public void testAgregarSuscripcion() {
		int resultadoEsperado = manejadorP.getPaquetes(true).size() + 1;
		manejadorP.agregarSuscripcion("topic/test");
		
		int resultadoReal = manejadorP.getPaquetes(true).size();
		
		assertEquals(resultadoEsperado, resultadoReal);
		assertEquals("topic/test", manejadorP.getPaquetes(true).get(resultadoReal - 1).getTopic());
		assertEquals(true, manejadorP.getPaquetes(true).get(resultadoReal - 1).esSuscripcion());
	}

	@Test
	public void testAgregarPublicacion() {
		int resultadoEsperado = manejadorP.getPaquetes(false).size() + 1;
		manejadorP.agregarPublicacion("topic/test");
		
		int resultadoReal = manejadorP.getPaquetes(false).size();
		
		assertEquals(resultadoEsperado, resultadoReal);
		assertEquals("topic/test", manejadorP.getPaquetes(false).get(resultadoReal - 1).getTopic());
		assertEquals(false, manejadorP.getPaquetes(false).get(resultadoReal - 1).esSuscripcion());
	}

	@Test
	public void testEditarPaquete1() {
		//Trabajamos con un paquete suscripcion -> esSuscripcion() = true
		manejadorP.agregarSuscripcion("topic/test");
		
		String resultadoEsperado1 = "topic/editar";
		boolean resultadoEsperado2 = true;
		manejadorP.editarPaquete(0, resultadoEsperado1, resultadoEsperado2);
		
		Paquete resultadoReal = manejadorP.getPaquete(0, true);
		
		assertEquals(resultadoEsperado1, resultadoReal.getTopic());
		assertEquals(resultadoEsperado2, resultadoReal.esSuscripcion());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testEditarPaquete2() {
		//Tengo 50 paquetes y quiero editar un paquete inexistente (indice)
		for(int i=0; i<50; i++) {
			manejadorP.agregarPublicacion("topic/test");
		}
		manejadorP.editarPaquete(-10, "topic/nuevo", false);
	}

	@Test
	public void testEliminarPaquete1() {
		for(int i=0; i<10; i++) {
			manejadorP.agregarSuscripcion("topic/test");
			manejadorP.agregarPublicacion("topic/test");
		}
		
		int resultadoEsperado1 = manejadorP.getPaquetes(true).size() - 1;
		int resultadoEsperado2 = manejadorP.getPaquetes(false).size() - 1;
		manejadorP.eliminarPaquete(0, true);
		manejadorP.eliminarPaquete(0, false);
		
		int resultadoReal1 = manejadorP.getPaquetes(true).size();
		int resultadoReal2 = manejadorP.getPaquetes(false).size();
		
		assertEquals(resultadoEsperado1, resultadoReal1);
		assertEquals(resultadoEsperado2, resultadoReal2);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testEliminarPaquete2() {
		manejadorP.eliminarPaquete(-150, true);
	}

	@Test
	public void testGetPaquete() {
		String resultadoEsperado = "topic/pub";
		manejadorP.agregarPublicacion(resultadoEsperado);
		
		int resultadoReal = manejadorP.getPaquetes(false).size();
		
		assertEquals(resultadoEsperado, manejadorP.getPaquete(resultadoReal - 1, false).getTopic());
	}

}
