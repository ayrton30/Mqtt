import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		//*Prueba de la clase Configuracion
		/*Configuracion config = new Configuracion(b1);
		config.setTopic_sensor("nuevoT/temp", "1");
		config.setTopic_sensor("nuevoT/LED", "3");*/
		
//Test FINAL:
		ModeloApp mqttApp = new ModeloApp("ClienteID", "Brokers.txt");
		mqttApp.setManejadorListener(new MyMensajeEventListener());	//Maneja el evento impriendo el mensaje Recibido
		
		mqttApp.agregarBroker("Broker1", "tcp://mqtt.fi.mdp.edu.ar", 1883);
		mqttApp.agregarBroker("Broker2E", "nuevoHost", -1234);
		mqttApp.agregarBroker("Broker3E", "segundoHost", 1111);
				
		//mqttApp.setEstrategiaBinaria(); //Podemos settear la estrategia que usa un archivo binario .dat
		
		System.out.println("\n\nImprimimos todos los brokers almacenados:");
		for(int i=0; i < mqttApp.getBrokers().size(); i++) {
			System.out.println(i + ") " + mqttApp.getBrokers().get(i).toString());
		}
		
		//Intento suscribirme sin haberme conectado a algun broker antes
		mqttApp.suscribirse("Nombre_S1", "topic/nuevo");
		
		//Me conecto al Broker 0 -> mqtt.fi.mdp.edu.ar:1883
		if (mqttApp.conectarse(0) == true) {
			System.out.println("\nConectado");
		}
		//mqttApp.desconectarse();

//Prueba de suscripcion y guardado correcto de los mensajes
		mqttApp.suscribirse("Nombre_S1", "topic/nuevo");
		mqttApp.suscribirse("Nombre_S2", "topic/otro");
		
		ArrayList<Paquete> sus = mqttApp.getSuscripciones();
		System.out.println("Cantidad de suscripciones en topics diferentes: " + sus.size());
		for(int i=0; i<sus.size(); i++) {
			System.out.println("PaqueteSuscripcion[" + i + "] " + sus.get(i).getTopic());
		}
		
		sus.get(0).imprimirMensajes();	//Mensajes almacenados para la suscripcion del topic: topic/nuevo
		sus.get(1).imprimirMensajes();	//Lo mismo pero para el topic: topic/otro*/
	

//Prueba de publicacion
		//mqttApp.publicar("topic/temp", "{Temp: 25.6° C}");
		
		for(int i = 100; i < 150; i++) {
			String mensaje = String.valueOf(i);
			
			mqttApp.publicar("Nombre_P1", "topic/numero", mensaje);
		}
		
		ArrayList<Paquete> pub = mqttApp.getPublicaciones();	//Obtengo todos los paquetes de publicaciones asociados al brokerActivo(con sus diferentes topics)
		System.out.println("Cantidad de publicaciones en topics diferentes: " + pub.size());
		for(int i=0; i<pub.size(); i++) {
			System.out.println("PaquetePublicacion[" + i + "] " + pub.get(i).getTopic());
		}
		pub.get(0).imprimirMensajes();	//Tambien se podria usar el metodo del paquete 'ArrayList<String> getMensajes()'	
	}
}