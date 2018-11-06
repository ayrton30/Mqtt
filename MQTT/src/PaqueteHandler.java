import java.io.Serializable;
import java.util.ArrayList;

// Funcionalidad: esta clase es la encargada de controlar los paquetes asociados a un broker
// Clasifica los paquetes según su funcionalidad en dos listas.

public class PaqueteHandler implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Paquete> paquetesS;	//Lista con todos los paquetes de tipo Suscripción, para usar en SuscribirActivity
	private ArrayList<Paquete> paquetesP;	//Lista pero con todos los paquetes de tipo Publicación -> PublicarActivity
	
	
	public PaqueteHandler() {
		this.paquetesS = new ArrayList<>();
		this.paquetesP = new ArrayList<>();
	}
	
	
	//No va a ser lo mismo generar un paquete para una suscripcion que para una publicacion, ya que tienen diferente funcionalidad
	public void agregarSuscripcion(String nombre, String topic) {
		Paquete pTemp = new Paquete(nombre, topic, true);	//TRUE -> Se trata de una suscripción
		this.paquetesS.add(pTemp);
	}
	
	public void agregarPublicacion(String nombre, String topic) {
		Paquete pTemp = new Paquete(nombre, topic, false);	//FALSE -> Estamos hablando de una publicacion
		this.paquetesP.add(pTemp);
	}
	
	//Le cambio el topic a un paquete y le borro todos sus mensajes asociados!
	public void editarPaquete(int i, String topic, boolean esSuscripcion) throws IndexOutOfBoundsException {
		if(esSuscripcion == true) {
			this.paquetesS.get(i).setTopic(topic);
			this.paquetesS.get(i).limpiarMensajes();
		}
		else {
			this.paquetesP.get(i).setTopic(topic);
			this.paquetesP.get(i).limpiarMensajes();
		}
	}
		
	public void eliminarPaquete(int i, boolean esSuscripcion) throws IndexOutOfBoundsException {
		if(esSuscripcion == true) {
			this.paquetesS.remove(i);
		}
		else {
			this.paquetesP.remove(i);
		}
	}
		
	public Paquete getPaquete(int i, boolean esSuscripcion) throws IndexOutOfBoundsException {
		if(esSuscripcion == true) {
			return(this.paquetesS.get(i));
		}
		else {
			return(this.paquetesP.get(i));
		}
	}
	
	public ArrayList<Paquete> getPaquetes(boolean esSuscripcion){
		if(esSuscripcion == true) {
			return(this.paquetesS);
		}
		else {
			return(this.paquetesP);
		}
	}


}
