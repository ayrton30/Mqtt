import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;


// Funcionalidad: contiene al cliente Paho Mqtt, realiza las conexiones, suscripciones y publicaciones.
public class Cliente {
	private MqttClient cliente;
	private String id_cliente;
	private BrokerHandler manejadorBrokers;
	private int iBrokerActivo;	//Posicion del Broker al cual se encuentra conectado el cliente
	
	
	public Cliente(String id) {
		this.manejadorBrokers = new BrokerHandler();
		
		this.iBrokerActivo = -1;	// Aún no existe un broker activo
		this.id_cliente = id;
	}
	
	public boolean conectarse(int i) {
		try {
			Broker brokerTemp = this.manejadorBrokers.getBroker(i);
			String ServerURI = brokerTemp.toString();
			
			cliente = new MqttClient(ServerURI, this.id_cliente);
			cliente.connect();
			
			this.setBrokerActivo(i);
			
			return(cliente.isConnected());
			
		} catch (MqttException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void desconectarse() {
		if(cliente.isConnected()) {
			try {
				cliente.disconnect();
				
			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("No hay conexion activa");
		}
	}
	
	public boolean setBrokerActivo(int i) {
		if(i < this.manejadorBrokers.getBrokers().size()) {
			this.iBrokerActivo = i;
			return true;
		}
		else {
			System.out.println("\nIngrese un indice valido");
			this.iBrokerActivo = -1;
			return false;
		}
	}
	
	public Broker getBrokerActivo() {
		if(this.brokerActivo()) {
			return(this.manejadorBrokers.getBroker(this.iBrokerActivo));
		}
		else {
			System.out.println("\nIngrese un indice valido");
			return null;
		}
	}
	
	//Puedo chequear si hay un Broker Activo setteado o no
	public boolean brokerActivo() {
		if(this.iBrokerActivo >= 0) {
			return true;
		}
		else {
			return false;
		}
	}
		
//Funcionalidad de los paquetes (Suscripciones|Publicaciones)
	
	public void addSuscripcion(String topic) {
		//Verifico que halla un broker configurado como activo y que el paquete que voy a crear ya no exista!
		if(brokerActivo()) {
			
			if(this.manejadorBrokers.getBroker(iBrokerActivo).indicePaquete(topic, true) == -1) {
				//El paquete NO EXISTE, por lo tanto lo creo y almaceno!
				
				this.manejadorBrokers.getBroker(iBrokerActivo).getManejadorPaquetes().agregarSuscripcion(topic);
			}
			
			//Le envio al constructor el manejador de paquetes y el broker activo
			cliente.setCallback(new MyMqttCallback(this.manejadorBrokers, this.iBrokerActivo));
			
			try {
				cliente.subscribe(topic);
			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("\n***Primero conectarse a un broker!***");
		}
     }
	
	public void addPublicacion(String topic, String mensaje) {
		if(brokerActivo()) {
			if(this.manejadorBrokers.getBroker(iBrokerActivo).indicePaquete(topic, true) == -1) {
				this.manejadorBrokers.getBroker(iBrokerActivo).getManejadorPaquetes().agregarPublicacion(topic);
			}
			
			MqttMessage mensaje_publicar = new MqttMessage();
			mensaje_publicar.setPayload(mensaje.getBytes());
			
			try {
				this.cliente.publish(topic, mensaje_publicar);
				
				int posicion = this.manejadorBrokers.getBroker(iBrokerActivo).indicePaquete(topic, false);
	        	//Me quedo con la posicion del paquete de PUBLICACION
	        	
				manejadorBrokers.getBroker(iBrokerActivo).getManejadorPaquetes().getPaquete(posicion, true).guardarMensaje(mensaje);
				manejadorBrokers.guardarArchivo();
	        	
	        	System.out.println("Mensaje *" + mensaje + "* publicado!");
	        	
			} catch (MqttPersistenceException e) {
				e.printStackTrace();
			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("\n***Primero conectarse a un broker!***");
		}
	}
	
//IMPORTANTE: solo se puede editar, eliminar u obtener paquetes (suscripciones y publicaciones) del broker activo configurado
	public boolean editarSuscripcion(int i, String topic) {
		if(brokerActivo()) {
			this.getBrokerActivo().getManejadorPaquetes().editarPaquete(i, topic, true);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean editarPublicacion(int i, String topic) {
		if(brokerActivo()) {
			this.getBrokerActivo().getManejadorPaquetes().editarPaquete(i, topic, false);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean eliminarSuscripcion(int i) {
		if(brokerActivo()) {
			this.getBrokerActivo().getManejadorPaquetes().eliminarPaquete(i, true);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean eliminarPublicacion(int i) {
		if(brokerActivo()) {
			this.getBrokerActivo().getManejadorPaquetes().eliminarPaquete(i, false);
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Paquete> getSuscripciones(){
		return(this.getBrokerActivo().getManejadorPaquetes().getPaquetes(true));
	}
	
	public ArrayList<Paquete> getPublicaciones(){
		return(this.getBrokerActivo().getManejadorPaquetes().getPaquetes(false));
	}
	
//Para podes usar los metodos de este Handlers desde la clase ModeloApp
	public BrokerHandler getManejadorB() {
		return(this.manejadorBrokers);
	}
	
}
