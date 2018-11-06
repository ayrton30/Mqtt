import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ModeloApp implements Serializable{
	private static final long serialVersionUID = 1L;
	private Cliente clienteFinal;
	private Configuracion config;
	
	/**
	 * Crea un objeto de tipo ModeloApp que corresponde a la entrada de la aplicacion Android.
	 * @param id_client es el nombre identificación del cliente en la conexión
	 */
	public ModeloApp(String id_client, String path) {
		this.clienteFinal = new Cliente(id_client, path); //ClienteID:string por defecto
		this.config = new Configuracion();
	}
	
	/**
	 * Agrega un {@code Broker} al {@code ArrayList<Brokers>} 
	 * del {@code Cliente}
	 * @param  host {@code String}
	 * @param  port {@code int}
	 */
	public void agregarBroker(String nombre, String host, int port) {
		this.clienteFinal.getManejadorB().agregarBroker(nombre, host, port);
	}
	
	/**
	 * Edita un {@code Broker} en el {@code ArrayList<Brokers>} 
	 * del {@code Cliente}
	 * @param i posicion del Broker en el Array
	 * @param host {@code String}
	 * @param port {@code int}
	 */
	public void editarBroker(int i, String host, int port) {
		this.clienteFinal.getManejadorB().getBroker(i).setHost(host);
		this.clienteFinal.getManejadorB().getBroker(i).setPort(port);
	}
	
	/**
	 * Elimina un {@code Broker} en el {@code ArrayList<Brokers>} 
	 * del {@code Cliente}
	 * @param i posicion del Broker en el Array
	 */
	public void eliminarBroker(int i) {
		this.clienteFinal.getManejadorB().eliminarBroker(i);	
	}
	
	/**
	 * @return {@code ArrayList<Broker>} contiene todos los Brokers almacenados por el usuario
	 */
	public ArrayList<Broker> getBrokers(){
		return(this.clienteFinal.getManejadorB().getBrokers());
	}
	
	public Broker getBroker(int i) throws IndexOutOfBoundsException  {
		return(this.clienteFinal.getManejadorB().getBroker(i));
	}
	
	public ArrayList<Paquete> getSuscripciones(){
		return(this.clienteFinal.getSuscripciones());
	}
	
	public ArrayList<Paquete> getPublicaciones(){
		return(this.clienteFinal.getPublicaciones());
	}
	
	public Paquete getPaqueteSuscripcion(int i) {
		return(this.clienteFinal.getPaqueteSuscripcion(i));
	}
	
	public Paquete getPaquetePublicacion(int i) {
		return(this.clienteFinal.getPaquetePublicacion(i));
	}
/*	
	public void setEstrategiaTexto() {
		this.clienteFinal.getManejadorB().setEstrategiaTexto();
	}*/
	
	public void setEstrategiaBinaria() {
		this.clienteFinal.getManejadorB().setEstrategiaBinaria();
	}
	
	public void setManejadorListener(MensajeEventListener listener) {
		this.clienteFinal.setManejadorEventListener(listener);
	}

	
	public boolean conectarse(int i) {
		return(this.clienteFinal.conectarse(i));
	}
	
	public void desconectarse() {
		this.clienteFinal.desconectarse();
	}
	
	public void guardarBrokers() throws FileNotFoundException, IOException {
		this.clienteFinal.getManejadorB().guardarArchivo();
	}
	
	/**
	 * Suscribe con los datos del Broker activo en el topic
	 * que se pasa como argumento
	 * @param  topic {@code String}
	 */
	
	//Posicion del broker a utilizar y topic
	public void suscribirse(String nombre, String topic) {
		this.clienteFinal.addSuscripcion(nombre, topic);
	}
	
	public void publicar(String nombre, String topic, String mensaje) {
		this.clienteFinal.addPublicacion(nombre, topic, mensaje);
	}
	
	public boolean editarSuscripcion(int i, String topic) {
		return(this.clienteFinal.editarSuscripcion(i, topic));
	}
	
	public boolean editarPublicacion(int i, String topic) {
		return(this.clienteFinal.editarPublicacion(i, topic));
	}
	
	public boolean eliminarSuscripcion(int i) {
		return(this.clienteFinal.eliminarSuscripcion(i));
	}
	
	public boolean eliminarPublicacion(int i) {
		return(this.clienteFinal.eliminarPublicacion(i));
	}
	
	//Configuracion, necesita el broker al que se debe conectar
	public void configuracion(Broker bActivo) {
		this.config.setBrokerActivo(bActivo);
	}
	
	public void setTopic_temp(String nuevo_topic) {
		this.config.setTopic_sensor(nuevo_topic, "1");
	}
	
	public void setTopic_distancia(String nuevo_topic) {
		this.config.setTopic_sensor(nuevo_topic, "2");
	}
	
	public void setTopic_led(String nuevo_topic) {
		this.config.setTopic_sensor(nuevo_topic, "3");
	}
}
