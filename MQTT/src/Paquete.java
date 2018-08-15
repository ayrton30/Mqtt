import java.io.Serializable;
import java.util.ArrayList;

public class Paquete implements Serializable{
	private boolean esSuscripcion;	//Indica si el paquete se trata de una suscripcion o publicacion
	private Broker broker;
	private String topic;
	private ArrayList<String> mensajes; //Coleccion de mensajes que llegan del topic como mensajes
										//o que son enviados a un topic especifico
	
	//Paquete preparado para una suscripción o publicación
	public Paquete(Broker b, String t, boolean s) {
		this.broker = new Broker(b.getHost(), b.getPort());
		this.topic = t;
		this.mensajes = new ArrayList<>();
		this.esSuscripcion = s;
	}
	
	public void setTopic(String t) {
		topic = t;
	}
	
	public void setBroker(String host, int port) {
		this.broker = new Broker(host, port);
	}
	
	public String getTopic() {
		return (this.topic);
	}
	
	public Broker getBroker() {
		return (this.broker);
	}
	
	//Indica si el paquete se trata de una suscripcion.
	public boolean esSuscripcion() {
		return this.esSuscripcion;
	}
	
	public ArrayList<String> getMensajes(){
		return (this.mensajes);
	}
	
	//De uso interno, es decir, cada paquete sabe como guardar sus propios mensajes!
	public void guardarMensaje(String m) {
			mensajes.add(m);
	}
	
	public void limpiarMensajes() {
		this.mensajes.clear();
	}
	
	public void imprimirMensajes() {
		System.out.println("Mensajes");
		for(int i=0; i<this.mensajes.size(); i++) {
			System.out.println(this.mensajes.get(i));
		}
		System.out.println("Fin de mensajes");
	}
	
	@Override
    public String toString() {
        return ("Broker: " + this.broker.toString() + "\nTopic: " + topic);
    }
}
