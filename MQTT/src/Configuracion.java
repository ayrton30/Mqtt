import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Configuracion {
	private MqttClient cliente;
	
	public Configuracion() {
		//
	}
	
	public void setBrokerActivo(Broker bActivo) {
		try {
			this.cliente = new MqttClient(bActivo.toString(), "clienteID");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//La finalidad de este metodo es poder cambiar los topics donde los sensores conectados a la ESP8266 van a publicar
	//los datos obtenidos.
	public void setTopic_sensor(String nuevo_topic, String opcion) {
		
		String topic_escucha = ""; 	//Topic en el cual la ESP8266 va a estar suscripto para poder recibir el nuevo topic
					
		MqttMessage mensaje = new MqttMessage();
		mensaje.setPayload(nuevo_topic.getBytes());
		
		switch(opcion) {
		case "1":
			topic_escucha = "config/temp";
			break;
		case "2":
			topic_escucha = "config/dist";
			break;
		case "3":
			topic_escucha = "config/led";
			break;
		}
		try {
			this.cliente.connect();
			this.cliente.publish(topic_escucha, mensaje);
			this.cliente.disconnect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
