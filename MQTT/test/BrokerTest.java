import static org.junit.Assert.*;
import org.junit.Test;

public class BrokerTest {
	
	@Test
	public void testSetHost() {
		Broker brokerTemp = new Broker("name_TEST", "Prueba", 1883);
		boolean resultadoReal = brokerTemp.setHost("Prueba");
		boolean resultadoEsperado = true;
		assertEquals(resultadoEsperado, resultadoReal);
	}

	@Test
	public void testSetPort1() {
		Broker brokerTemp = new Broker("name_TEST", "Prueba", 1883);
		boolean resultadoReal = brokerTemp.setPort(5555);
		boolean resultadoEsperado = true;
		assertEquals(resultadoEsperado, resultadoReal);
	}
	
	@Test
	public void testSetPort2() {
		Broker brokerTemp = new Broker("name_TEST", "Prueba", 1883);
		boolean resultadoReal = brokerTemp.setPort(-5555);
		boolean resultadoEsperado = false;
		assertEquals(resultadoEsperado, resultadoReal);
	}
}
