import java.io.Serializable;
import java.util.ArrayList;

// Funcionalidad: almacenar los valores del host y port al cual el cliente puede conectarse
// para suscribirse y/o publicar mensajes. Tambien tiene un manejador de Paquetes, el cual contiene
// todos los mensajes recibidos/enviados.

public class Broker implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String host;
	private int port;
	private PaqueteHandler manejadorPaquetes;
	
	
	//Constructor
	public Broker(String n, String h, int p) {
		setNombre(n);
		setHost(h);
		setPort(p);
		this.manejadorPaquetes = new PaqueteHandler();
	}

	//Setters y Getters
	public boolean setHost(String valor) {
		this.host = valor;
		return true;
	}
	
	public boolean setNombre(String valor) {
		this.nombre = valor;
		return true;
	}

	//Se espera un n�mero de puerto positivo
	public boolean setPort(int valor) {
		if (valor > 0) {
			this.port = valor;
			return true;
		} else {
			this.port = Math.abs(valor);
			return false;
		}
	}

	public String getHost() {
		return(this.host);
	}

	public int getPort() {
		return(this.port);
	}
	
	public String getNombre() {
		return(this.nombre);
	}
	
	public PaqueteHandler getManejadorPaquetes() {
		return(this.manejadorPaquetes);
	}
	
	//Para obtener el �ndice de un paquete con un topic especifico dentro de un broker.
	//El boolean indica si estoy buscando paquetes de tipo suscripcion o publicacion
	public int indicePaquete(String topic, boolean esSuscripcion) {
		int posicion = -1;
		
		if(esSuscripcion == true) {
			ArrayList<Paquete> susTemp = this.manejadorPaquetes.getPaquetes(esSuscripcion);
			
			for(int i = 0; i < susTemp.size(); i++) {
				if(susTemp.get(i).getTopic().equals(topic)) {
					posicion = i;
				}
			}
		}
		else {
			ArrayList<Paquete> PubTemp = this.manejadorPaquetes.getPaquetes(esSuscripcion);
			
			for(int i = 0; i < PubTemp.size(); i++) {
				if(PubTemp.get(i).getTopic().equals(topic)) {
					posicion = i;
				}
			}
		}
		return posicion;
	}

	//M�todo usado para luego poder mostrar/imprimir los datos de un broker
	public String toString() {
		return (this.getHost() + ":" + String.valueOf(this.getPort()));
	}
}