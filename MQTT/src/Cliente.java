import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class Cliente {
	private BrokerHandler manejadorBroker;
	private int iBrokerActivo;	//Posicion del Broker al cual se encuentra conectado el cliente
	private PaqueteHandler manejadorPaquete;	//Conjunto de suscripciones y publicaciones para el cliente actual
	private MqttClient cliente;
	private String id_cliente;
	
	
	public Cliente(String id) {
		this.manejadorBroker = new BrokerHandler();
		this.manejadorPaquete = new PaqueteHandler();
		this.iBrokerActivo = -1;
		
		this.id_cliente = id;
	}
	
	public boolean setBrokerActivo(int i) {
		if(i < this.manejadorBroker.getBrokers().size()) {
			this.iBrokerActivo = i;
			return true;
		}
		else {
			System.out.println("\nIngrese un indice valido");
			this.iBrokerActivo = -1;
			return false;
		}
	}
	
	public Broker getBrokerActivo(int i) {
		if(i < this.manejadorBroker.getBrokers().size()) {
			return(this.manejadorBroker.getBroker(i));
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
	
	public boolean conectarse(int i) {
		//setBrokerActivo devuelve true si encontro la posicion del broker
		if(this.setBrokerActivo(i)) {
			//A partir de la posicion del Broker en el manejador de brokers
			Broker brokerTemp = this.manejadorBroker.getBroker(i);
			String ServerURI = brokerTemp.toString();
			
			try {
				cliente = new MqttClient(ServerURI, this.id_cliente);
				cliente.connect();
				
				return(cliente.isConnected());
				
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	public void desconectarse() {
		if(cliente.isConnected()) {
			try {
				cliente.disconnect();
				
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("No hay conexion activa");
		}
	}
	
		
//Funcionalidad de los paquetes (Suscripciones|Publicaciones)
	
	public void addSuscripcion(String topic) {
		//Verifico que halla un broker configurado como activo 
		//y que el paquete que voy a crear ya no exista!
		if(brokerActivo()) {
			if(manejadorPaquete.indicePaquete(topic, manejadorBroker.getBroker(iBrokerActivo), true) == -1) {
				//El paquete NO EXISTE, por lo tanto lo creo y almaceno!
				Broker bActivo = this.manejadorBroker.getBroker(iBrokerActivo);
				this.manejadorPaquete.addPaqueteSuscripcion(bActivo, topic);	
			}
			
			//Le envio al constructor el manejador de paquetes y el broker activo
			cliente.setCallback(new MyMqttCallback(manejadorPaquete, manejadorBroker.getBroker(iBrokerActivo)));
			
			try {
				cliente.subscribe(topic);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("\n***Primero conectarse a un broker!***");
		}
     }
	
	public void addPublicacion(String topic, String mensaje) {
		if(brokerActivo()) {
			if(manejadorPaquete.indicePaquete(topic, manejadorBroker.getBroker(iBrokerActivo), true) == -1) {
				Broker bActivo = this.manejadorBroker.getBroker(iBrokerActivo);
				this.manejadorPaquete.addPaquetePublicacion(bActivo, topic);
			}
			
			MqttMessage mensaje_publicar = new MqttMessage();
			mensaje_publicar.setPayload(mensaje.getBytes());
			
			try {
				this.cliente.publish(topic, mensaje_publicar);
				
				int posicion = manejadorPaquete.indicePaquete(topic, manejadorBroker.getBroker(iBrokerActivo), false);
	        	//La posicion del paquete que coinciden con el topic del mensaje recibido
	        	//Me quedo con el unico paquete de PUBLICACION
	        	
				manejadorPaquete.getPaquete(posicion).guardarMensaje(mensaje);
	        	manejadorPaquete.guardar(this.manejadorPaquete.getPaquetes());
	        	
	        	System.out.println("Mensaje *" + mensaje + "* publicado!");
	        	
			} catch (MqttPersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("\n***Primero conectarse a un broker!***");
		}
		
	}
	
//Para podes usar los metodos de estos Handlers desde la clase ModeloApp
	public BrokerHandler getManejadorB() {
		return(this.manejadorBroker);
	}
	
	public PaqueteHandler getManejadorP() {
		return(this.manejadorPaquete);
	}

}
