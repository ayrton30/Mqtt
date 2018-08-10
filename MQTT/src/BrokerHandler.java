import java.util.ArrayList;
import java.util.Iterator;

public class BrokerHandler {
	private ArrayList<Broker> brokers;
	private EstrategiaArchivo estrategia;
	
	public BrokerHandler() {
		this.brokers = new ArrayList<>();
		//Por defecto se elige la estrategia de archivo de texto
		this.setEstrategiaTexto();
	}
	
	public void agregarBroker(Broker b) {
		Broker brokerTemp = new Broker(b.getHost(), b.getPort());
        brokers.add(brokerTemp);
	}
	
	public void agregarBroker(String host, int port) {
		Broker brokerTemp = new Broker(host, port);
		brokers.add(brokerTemp);
	}
	
	public boolean editarBroker(int i, Broker b) {
		if(i < brokers.size()) {
			brokers.get(i).setHost(b.getHost());
			brokers.get(i).setPort(b.getPort());
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean eliminarBroker(int i) {
		if(i < brokers.size()) {
			brokers.remove(i);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void guardarArchivo() {
		//Según la estrategia seleccionda (polimorfismo)
		this.estrategia.guardar(this.brokers);
	}
	
	public void leerArchivo() {
		//Según la estrategia seleccionada
		 this.brokers = this.estrategia.leer();
	}
	
	public void imprimir() {
		Iterator <Broker> i = brokers.iterator();
		while(i.hasNext()) {
			System.out.println(i.next().toString());
		}
	}
	
	public Broker getBroker(int i) {
		return (this.brokers.get(i));
	}
	
	public void setEstrategiaTexto() {
		this.estrategia = new EstrategiaTexto();
	}
	
	public void setEstrategiaOtra() {
		this.estrategia = new EstrategiaOtra();
	}
}
