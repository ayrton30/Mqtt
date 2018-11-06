import java.io.Serializable;
import java.util.ArrayList;

// Funcionalidad: objeto que contiene datos de un topic especifico y 
// los mensajes recibidos o enviados a este

public class Paquete implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean esSuscripcion;	//Indica si el paquete se trata de una suscripcion o publicacion
	private String topic;
	private String nombre;
	private ArrayList<String> mensajes; //Coleccion de mensajes que llegan del topic como mensajes
										//o que son enviados a un topic especifico como publicación
	
//Paquete preparado para una suscripción o publicación
	public Paquete(String nombre, String topic, boolean esSuscripcion) {
		this.setTopic(topic);
		this.setNombre(nombre);
		this.esSuscripcion = esSuscripcion;		//No hay un set_esSuscripcion(boolean) debido a que este valor no deberia cambiar en ningun momento
		this.mensajes = new ArrayList<>();
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getTopic() {
		return(this.topic);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return(this.nombre);
	}
	
	//Indica si el paquete se trata de una suscripcion. 
	//| true -> Paquete de tipo Suscripción | ; | false -> Paquete de tipo Publicación |
	public boolean esSuscripcion() {
		return(this.esSuscripcion);
	}
	
	public ArrayList<String> getMensajes(){
		return(this.mensajes);
	}
	
	//Cada paquete sabe como guardar sus propios mensajes
	//String mensaje puede ser de una suscripcion (mensajes que llegan) o que son publicados en un topic
	public void guardarMensaje(String mensaje) {
			this.mensajes.add(mensaje);
	}
	
	public void limpiarMensajes() {
		this.mensajes.clear();
	}
	
	
	public void imprimirMensajes() {
		System.out.println("\nMensajes");
		for(int i=0; i<this.mensajes.size(); i++) {
			System.out.println(this.mensajes.get(i));
		}
		System.out.println("Fin de los mensajes");
	}
}
