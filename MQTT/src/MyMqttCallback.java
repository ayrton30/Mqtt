import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyMqttCallback implements MqttCallback {

	private PaqueteHandler manejadorPaquete;
	private Broker brokerActivo;
	
	public MyMqttCallback(PaqueteHandler p, Broker bActivo) {
		this.manejadorPaquete = p;
		this.brokerActivo = bActivo;
	}
	
	@Override
	public void connectionLost(Throwable arg0) {
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
	}

	@Override
	public void messageArrived(String topic, MqttMessage Mensaje) throws Exception {
		int posicion = manejadorPaquete.indicePaquete(topic, this.brokerActivo, true);
    	//La posicion del paquete que coincide con el topic del mensaje recibido.
		//Siempre va a existir un paquete que coincida!
		//El true permite buscar los paquetes de tipo SUSCRIPCION
    	
/*		System.out.println("Cantidad de posiciones: " + posiciones.size());
		System.out.println("Cantidad de paquetes: " + this.manejadorPaquete.getPaquetes().size()); */
		
   		manejadorPaquete.getPaquete(posicion).guardarMensaje(Mensaje.toString());
   		manejadorPaquete.guardar(manejadorPaquete.getPaquetes());
   		
    	//Para imprimir todos los mensajes de un paquete
    	//manejadorPaquete.getPaquete(posiciones.get(i)).imprimirMensajes();
        
		System.out.println(Mensaje.toString());	
	}

}
