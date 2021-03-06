import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BrokerHandlerTest {

	BrokerHandler manejadorB = new BrokerHandler("Brokers.txt");
	
	@Test
	public void testAgregarBroker() {
		int resultadoEsperado = manejadorB.getBrokers().size() + 1;
		manejadorB.agregarBroker("name_TEST", "nuevoHost", 1883);
		
		int resultadoReal = manejadorB.getBrokers().size();
		
		assertEquals(resultadoEsperado, resultadoReal);
		assertEquals("nuevoHost", manejadorB.getBrokers().get(resultadoReal - 1).getHost());
		assertEquals(1883, manejadorB.getBrokers().get(resultadoReal - 1).getPort());
	}
	
	@Test
	public void testEditarBroker1() {
		Broker resultadoEsperado = new Broker("name_TEST", "editarHost", 1883);
		manejadorB.agregarBroker("name_TEST", "nuevoHost", 1883);
		
		manejadorB.editarBroker(0, resultadoEsperado.getHost(), resultadoEsperado.getPort());
		Broker resultadoReal = manejadorB.getBroker(0);
		
		assertEquals(resultadoEsperado.getHost(), resultadoReal.getHost());
		assertEquals(resultadoEsperado.getPort(), resultadoReal.getPort());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testEditarBroker2() {
		//Tengo 2 brokers y quiero editar un broker inexistente (indice)
		manejadorB.agregarBroker("name_TEST", "primerHost", 1883);
		manejadorB.editarBroker(-10, "nuevoHost", 9999);
	}
	
	@Test
	public void testEliminarBroker1() {
		int resultadoEsperado = manejadorB.getBrokers().size() -1;
		manejadorB.eliminarBroker(0);
		
		int resultadoReal = manejadorB.getBrokers().size();
		assertEquals(resultadoEsperado, resultadoReal);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testEliminarBroker2() {
		manejadorB.eliminarBroker(9999);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetBroker() {
		BrokerHandler manejadorB = new BrokerHandler("Brokers.txt");
		manejadorB.agregarBroker("name_TEST", "primerHost", 1883);
		manejadorB.getBroker(9999);
	}

}
