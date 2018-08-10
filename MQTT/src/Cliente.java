import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

//Falta correcta implementacion!

public class Cliente {
	
	private BrokerHandler manejadorB;
	
	public Cliente() {
		this.manejadorB = new BrokerHandler();
	}
	
	public BrokerHandler getBrokerHandler() {
		return (this.manejadorB);
	}
	
	/*
	protected String id_cliente;
	protected String tema;
	protected Broker broker;
	protected MqttClient tp2017;
	
	
	public void setCliente(String valor) {
		this.id_cliente = valor;
	}
	
	public String getCliente(){return this.id_cliente;}
	
	//Constructor
	public Cliente(Broker b, String id) {
		this.broker.setHost(b.getHost());
		this.broker.setPort(b.getPort());
		this.setCliente(id);
		
		
		// Realiza la conexion con el Broker interno, configura el Cliente!
		Integer port = this.broker.getPort();
		String direccion = this.broker.getHost() + ":" + port.toString();
		
		try {
			// MqttClient(serverURI, idClient) 
			// donde serverURI mantiene formato tcp://server:port
			
			this.tp2017 = new MqttClient(direccion, this.id_cliente);
			tp2017.connect();
			
			if(tp2017.isConnected()) {
				System.out.println("Conectado");
			}
			tp2017.disconnect();
			
		}
		catch (MqttException e) {
	        e.printStackTrace();
	    }
	}
	
	public void suscripcion(String topic) {
		
		this.tema = topic;
		MqttConnectOptions opc = new MqttConnectOptions();
		opc.setCleanSession(true);
		
		this.tp2017.setCallback(new MqttCallback() {
			public void connectionLost(Throwable cause) {}

		    public void messageArrived(String tema, MqttMessage mensaje) throws Exception {
		        System.out.println("Mensaje: " + mensaje.toString());
		    }

		    public void deliveryComplete(IMqttDeliveryToken token) {}
		});
		
		try {
			this.tp2017.connect(opc);
			this.tp2017.subscribe(tema);
			
		}
		catch(MqttException e) {
		    e.printStackTrace();
		}
	}
	
	public void darse_baja() {
		try {
			this.tp2017.unsubscribe(tema);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void publicacion(String topic, String mensaje) {
		
		MqttMessage mensaje_pub = new MqttMessage();
		mensaje_pub.setPayload(mensaje.getBytes());
		
		try {
			this.tp2017.connect();
			this.tp2017.publish(topic, mensaje_pub);
				
			System.out.println("Publicado: " + mensaje);
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
