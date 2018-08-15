import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PaqueteHandler {
	private ArrayList<Paquete> paquetes;
	
	public PaqueteHandler() {
		this.paquetes = new ArrayList<>();
		this.paquetes = this.leer();
	}
	
	//Si ocurre un cambio en un broker, este se edita en todos los paquetes que esten asociados a ese broker
	public void editarBrokerPaquetes(String host, int port) {
		Broker brokerTemp = new Broker(host, port);
		
		for(int i=0; i<paquetes.size(); i++) {
			if(paquetes.get(i).getBroker().toString().equals(brokerTemp.toString())) {
				paquetes.get(i).setBroker(host, port);
			}
		}
	}
	
	//Para obtener el indice de un paquete con un topic especifico, un broker.
	//El boolean indica si estoy buscando paquetes de tipo suscripcion o publicacion
	public int indicePaquete(String topic, Broker b, boolean esSuscripcion) {
		int posicion = -1;
		
		for(int i = 0; i < paquetes.size(); i++) {
			if(this.paquetes.get(i).getTopic().equals(topic) && 
					this.paquetes.get(i).esSuscripcion() == esSuscripcion &&
					this.paquetes.get(i).getBroker().toString().equals(b.toString())) {
				posicion = i;
			}
		}
		return posicion;
	}
	
	public void addPaqueteSuscripcion(Broker bActivo, String topic) {
		Paquete pTemp = new Paquete(bActivo, topic, true);	//TRUE -> Se trata de una suscripción
		this.paquetes.add(pTemp);
		
		//Al haber una modificación en el conjunto de paquetes se realiza un guardado completo.
		this.guardar(paquetes);
	}
	
	public void addPaquetePublicacion(Broker bActivo, String topic) {
		Paquete pTemp = new Paquete(bActivo, topic, false);	//FALSE -> Estamos hablando de una publicacion
		this.paquetes.add(pTemp);
		
		//Necesitamos un guardado
		this.guardar(paquetes);
	}
	
	public Paquete getPaquete(int i) {
		return this.paquetes.get(i);
	}
	
	public ArrayList<Paquete> getPaquetes(){
		return this.paquetes;
	}
	
	//Le cambio el topic a un paquete y le borro todos sus mensajes asociados!
	public void editar(int iPaquete, String topic) {
		this.paquetes.get(iPaquete).setTopic(topic);
		this.paquetes.get(iPaquete).limpiarMensajes();
	}
	
	public void eliminar(int i) {
		this.paquetes.remove(i);
		this.guardar(paquetes);
	}
	
	public void eliminar(Broker b) {
		for(int i=0; i<paquetes.size(); i++) {
			if(paquetes.get(i).getBroker().toString().equals(b.toString())) {
				eliminar(i);
			}
		}
	}
	

//Metodos de escritura y lectura	
	public void guardar(ArrayList<Paquete> p) {
		String fichero = "Paquetes.dat";
		 
        try {
            ObjectOutputStream ficheroSalida = new ObjectOutputStream(new FileOutputStream(fichero));
            ficheroSalida.writeObject(p);
            ficheroSalida.flush();
            ficheroSalida.close();
 
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: El fichero no existe. ");
        } catch (IOException ioe) {
            System.out.println("Error: Fallo en la escritura en el fichero. |10|");
        }
	}
	

	public ArrayList<Paquete> leer() {
		String fichero = "Paquetes.dat";
		ArrayList<Paquete> paquetesTemp = new ArrayList<>();
		
        try {
        	ObjectInputStream ficheroEntrada = new ObjectInputStream(new FileInputStream(fichero));
        	ArrayList<Paquete> readObject;
		
			readObject = (ArrayList <Paquete>)ficheroEntrada.readObject();
			 
			paquetesTemp = readObject;
            ficheroEntrada.close();
            
            return paquetesTemp;
 
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: El fichero no existe. ");
        } catch (IOException ioe) {
            System.out.println("Error: Fallo en la escritura en el fichero.|20|");
        } catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
        return paquetesTemp;
	}
}
