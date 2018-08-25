import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		//*Prueba de la clase Configuracion
		/*Configuracion config = new Configuracion(b1);
		config.setTopic_sensor("nuevoT/temp", "1");
		config.setTopic_sensor("nuevoT/LED", "3");*/
		
//Test FINAL:
		ModeloApp mqttApp = new ModeloApp("ClienteID");
		
		mqttApp.agregarBroker("tcp://mqtt.fi.mdp.edu.ar", 1883);
		mqttApp.agregarBroker("nuevoHost", -1234);
		mqttApp.agregarBroker("segundoHost", 1111);
				
		//mqttApp.setEstrategiaBinaria(); //Podemos settear la estrategia que usa un archivo binario .dat
		
		System.out.println("\n\nImprimimos todos los brokers almacenados:");
		for(int i=0; i < mqttApp.getBrokers().size(); i++) {
			System.out.println(i + ") " + mqttApp.getBrokers().get(i).toString());
		}
		
		//Intento suscribirme sin haberme conectado a algun broker antes
		mqttApp.suscribirse("topic/nuevo");
		
		if (mqttApp.conectarse(0) == true) {
			System.out.println("\nConectado");
		}
		//mqttApp.desconectarse();
		
		mqttApp.suscribirse("topic/nuevo");
		//mqttApp.suscribirse("topic/otro");
		mqttApp.publicar("topic/temp", "{Temp: 25.6° C}");
		
		for(int i=150 ; i < 170; i++) {
			String mensaje = String.valueOf(i);
			
			mqttApp.publicar("topic/numero", mensaje);
		}
		
		ArrayList<Paquete> pub = mqttApp.getPublicaciones();
		for(int i=0; i<pub.size(); i++) {
			System.out.println("PaquetePublicacion[" + i + "] " + pub.get(i).getTopic());
		}
		pub.get(9).imprimirMensajes();	//Tambien se podria usar el metodo del paquete 'ArrayList<String> getMensajes()'
		
	}
}
