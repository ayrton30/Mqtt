import org.junit.Test;

public class BrokerHandlerTest {

	BrokerHandler manejadorB = new BrokerHandler();
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testEditarBroker() {
		//Tengo 2 brokers y quiero editar un broker inexistente (indice)
		manejadorB.agregarBroker("primerHost", 1883);
		manejadorB.editarBroker(-10, "nuevoHost", 9999);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testEliminarBroker() {
		manejadorB.eliminarBroker(9999);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetBroker() {
		BrokerHandler manejadorB = new BrokerHandler();
		manejadorB.agregarBroker("primerHost", 1883);
		manejadorB.getBroker(9999);
	}

}
