import static org.junit.Assert.*;

import org.junit.Test;

public class PaqueteTest {

	Paquete paquete1, paquete2;
	
	@Test
	public void testPaquete() {
		paquete1 = new Paquete("topic/prueba/sus", true);	//Paquete de suscripción
		paquete2 = new Paquete("topic/prueba/pub", false);	//Paquete de publicación
		
		String resultadoEsperadoSus_1 = "topic/prueba/sus";	boolean resultadoEsperadoSus_2 = true;
		String resultadoEsperadoPub_1 = "topic/prueba/pub";	boolean resultadoEsperadoPub_2 = false;
		
		assertEquals(resultadoEsperadoSus_1, paquete1.getTopic());
		assertEquals(resultadoEsperadoSus_2, paquete1.esSuscripcion());
		assertEquals(resultadoEsperadoPub_1, paquete2.getTopic());
		assertEquals(resultadoEsperadoPub_2, paquete2.esSuscripcion());
	}

	@Test
	public void testGuardarMensaje() {
		paquete1 = new Paquete("topic/prueba/sus", true);
		
		for(int i=0; i<100; i++) {
			String mensaje = String.valueOf(i);
			paquete1.guardarMensaje(mensaje);
		}
		
		for(int i=0; i<100; i++) {
			String resultadoEsperado = String.valueOf(i);
			String resultadoReal = paquete1.getMensajes().get(i);
			
			assertEquals(resultadoEsperado, resultadoReal);
		}
		
		assertEquals("50", paquete1.getMensajes().get(50));
	}

	@Test
	public void testLimpiarMensajes() {
		paquete1 = new Paquete("topic/prueba/sus", true);
		
		for(int i=0; i<100; i++) {
			String mensaje = String.valueOf(i);
			paquete1.guardarMensaje(mensaje);
		}
		paquete1.limpiarMensajes();
		
		int resultadoEsperado = 0;	//Se espera que no exista ningun string en el array de mensajes
		assertEquals(resultadoEsperado, paquete1.getMensajes().size());
	}

}
