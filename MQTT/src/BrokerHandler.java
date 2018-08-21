import java.util.ArrayList;

// Funcionalidad: esta clase se encarga de manejar todos los brokers creados por el usuario, que luego seran 
// utilizados por el cliente. Permite la creación, edición y eliminación de brokers. 

public class BrokerHandler {
	private ArrayList<Broker> brokers;
	private EstrategiaArchivo estrategia;	// -> Patrón de estrategia
	
	
	public BrokerHandler() {
		this.brokers = new ArrayList<>();
		this.setEstrategiaTexto();	//Por defecto se elige la estrategia de archivo de texto
		
	//Lee los brokers persistidos!
		this.leerArchivo();
	}
	
	public void agregarBroker(String host, int port) {
		Broker brokerTemp = new Broker(host, port);
		brokers.add(brokerTemp);
		
		this.guardarArchivo();
	}
	
	public void editarBroker(int i, String host, int port) throws IndexOutOfBoundsException{
		this.brokers.get(i).setHost(host);
		this.brokers.get(i).setPort(port);
			
		this.guardarArchivo();	
	}
	
	public void eliminarBroker(int i) {
		this.brokers.remove(i);
		this.guardarArchivo();
	}
	
	public ArrayList<Broker> getBrokers(){
		leerArchivo();
		return(this.brokers);
	}
	
	public Broker getBroker(int i) throws IndexOutOfBoundsException {
		Broker brokerTemp = brokers.get(i);
		return brokerTemp;
	}
	
//Polimorfismo respecto a la persistencia de la información!
	
	public void guardarArchivo() {
		//El método guardar recibe como parámetro el ArrayList que se desea guardar en un archivo.
		//Según la estrategia seleccionado se guardara en un .txt o .dat
		
		this.estrategia.guardar(this.brokers);	//Según la estrategia seleccionda 
	}
	
	public void leerArchivo() {
		//El método leer devuelve un objeto de tipo ArrayList<Broker> con los datos obtenidos del archivo Brokers.txt
		//o Brokers.dat según la estrategia seleccionda
		this.brokers = this.estrategia.leer();	//Según la estrategia seleccionda 
	}
	
	public void setEstrategiaTexto() {
		this.estrategia = new EstrategiaTexto();
	}
	
	public void setEstrategiaBinaria() {
		this.estrategia = new EstrategiaBinaria();
	}
}
