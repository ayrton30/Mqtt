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
	
	public boolean editarBroker(int i, String host, int port) {
		if(i < brokers.size()) {
			this.brokers.get(i).setHost(host);
			this.brokers.get(i).setPort(port);
			
			this.guardarArchivo();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean eliminarBroker(int i) {
		if(i < brokers.size()) {
			brokers.remove(i);
			
			this.guardarArchivo();
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Broker> getBrokers(){
		leerArchivo();
		return(this.brokers);
	}
	
	public Broker getBroker(int i) throws IndexOutOfBoundsException  {
		try {
			Broker brokerTemp = brokers.get(i);
			return brokerTemp;
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Indice invalido");
		}
		return null;
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
