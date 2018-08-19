import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyMqttCallback implements MqttCallback {

	private BrokerHandler manejadorBrokers;
	private int iBrokerActivo;	//Posici�n del broker activo
	
	public MyMqttCallback(BrokerHandler manejador, int i) {
		this.manejadorBrokers = manejador;
		this.iBrokerActivo = i;
	}
	
	@Override
	public void connectionLost(Throwable arg0) {
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
	}

	@Override
	public void messageArrived(String topic, MqttMessage Mensaje) throws Exception {
		int posicion = this.manejadorBrokers.getBroker(iBrokerActivo).indicePaquete(topic, true);
    	//La posicion del paquete que coincide con el topic del mensaje recibido.
		//Siempre va a existir un paquete que coincida!
		//El true permite buscar los paquetes de tipo SUSCRIPCION
    	
/*		System.out.println("Cantidad de posiciones: " + posiciones.size());
		System.out.println("Cantidad de paquetes: " + this.manejadorPaquete.getPaquetes().size()); */
		
		this.manejadorBrokers.getBroker(iBrokerActivo).getManejadorPaquetes().getPaquete(posicion, true).guardarMensaje(Mensaje.toString());
		this.manejadorBrokers.guardarArchivo();
   		
    	//Para imprimir todos los mensajes de un paquete
		//this.manejadorBrokers.getBroker(iBrokerActivo).getManejadorPaquetes().getPaquete(posicion, true).imprimirMensajes();
        
		System.out.println(Mensaje.toString());	
	}
}
