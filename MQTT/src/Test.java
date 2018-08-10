public class Test {

	public static void main(String[] args) {
		
		BrokerHandler handler = new BrokerHandler();
		
		Broker b1 = new Broker("tcp://mqtt.fi.mdp.edu.ar", 1883);
		Broker b2 = new Broker("nuevoHost", 1234);
		
		handler.agregarBroker(b1);
		handler.agregarBroker(b2);
		handler.agregarBroker(new Broker("segundoHost", 5678));
		
		handler.editarBroker(2, new Broker("tercerHost", 9999));
		handler.editarBroker(100, b1); //No tiene efecto
		
		handler.imprimir();
		handler.guardarArchivo(); //Guardo un ArrayList de 3 Brokers
		
		handler.agregarBroker(new Broker("prueba", 6666)); //Agrego un nuevo Broker
		handler.leerArchivo(); 
		System.out.println("\nLuego de la lectura:");
		handler.imprimir();
		
		//Prueba de visibilidad
		Cliente nuevoC = new Cliente();
		
		nuevoC.getBrokerHandler().agregarBroker(new Broker("google.com", 1556));
		nuevoC.getBrokerHandler().imprimir();
	}
}
