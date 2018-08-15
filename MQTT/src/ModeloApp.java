import java.util.ArrayList;

public class ModeloApp {
	private Cliente clienteFinal;
	private Configuracion config;
	
	/**
	 * Crea un objeto de tipo ModeloApp que corresponde a la entrada de la aplicacion Android.
	 * @param id_client es el nombre identificación del cliente en la conexión
	 */
	public ModeloApp(String id_client) {
		this.clienteFinal = new Cliente(id_client); //ClienteID:string por defecto
		this.config = new Configuracion();
	}
	
	/**
	 * Agrega un {@code Broker} al {@code ArrayList<Brokers>} 
	 * del {@code Cliente}
	 * @param  host {@code String}
	 * @param  port {@code int}
	 */
	public void agregarBroker(String host, int port) {
		this.clienteFinal.getManejadorB().agregarBroker(host, port);
	}
	
	/**
	 * Edita un {@code Broker} en el {@code ArrayList<Brokers>} 
	 * del {@code Cliente}
	 * @param i posicion del Broker en el Array
	 * @param host {@code String}
	 * @param port {@code int}
	 */
	public void editarBroker(int i, String host, int port) {
		if(this.clienteFinal.getManejadorB().editarBroker(i, host, port)) {
			this.clienteFinal.getManejadorP().editarBrokerPaquetes(host, port);
		}
		
	}
	
	/**
	 * Elimina un {@code Broker} en el {@code ArrayList<Brokers>} 
	 * del {@code Cliente}
	 * @param i posicion del Broker en el Array
	 */
	public void eliminarBroker(int i) {
		if(this.clienteFinal.getManejadorB().eliminarBroker(i)) {
			this.clienteFinal.getManejadorP().eliminar(this.clienteFinal.getManejadorB().getBroker(i));
		}
		
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
/*	
	public void setEstrategiaTexto() {
		this.clienteFinal.getManejadorB().setEstrategiaTexto();
	}
	
	public void setEstrategiaBinaria() {
		this.clienteFinal.getManejadorB().setEstrategiaBinaria();
	}
*/
	
	public boolean conectarse(int i) {
		return(this.clienteFinal.conectarse(i));
	}
	
	public void desconectarse() {
		this.clienteFinal.desconectarse();
	}
	
	/**
	 * Suscribe con los datos del Broker activo en el topic
	 * que se pasa como argumento
	 * @param  topic {@code String}
	 */
	
	//Posicion del broker a utilizar y topic
	public void suscribirse(String topic) {
		this.clienteFinal.addSuscripcion(topic);
	}
	
	public void publicar(String topic, String mensaje) {
		this.clienteFinal.addPublicacion(topic, mensaje);
	}
	
	public void editarPaquete(int iPaquete, String topic) {
		this.clienteFinal.getManejadorP().editar(iPaquete, topic);
	}
	
	public void eliminarPaquete(int i) {
		this.clienteFinal.getManejadorP().eliminar(i);
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
