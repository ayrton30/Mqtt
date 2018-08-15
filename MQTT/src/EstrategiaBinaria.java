import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EstrategiaBinaria implements EstrategiaArchivo{

	@Override
	public void guardar(ArrayList<Broker> b) {
		String fichero = "Brokers.dat";
		 
        try {
            ObjectOutputStream ficheroSalida = new ObjectOutputStream(new FileOutputStream(fichero));
            ficheroSalida.writeObject(b);
            ficheroSalida.flush();
            ficheroSalida.close();
 
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: El fichero no existe. ");
        } catch (IOException ioe) {
            System.out.println("Error: Fallo en la escritura en el fichero. ");
        }
	}

	@Override
	public ArrayList<Broker> leer() {
		String fichero = "Brokers.dat";
		ArrayList<Broker> brokersTemp = new ArrayList<>();
		
        try {
        	ObjectInputStream ficheroEntrada = new ObjectInputStream(new FileInputStream(fichero));
        	ArrayList<Broker> readObject;
		
			readObject = (ArrayList <Broker>) ficheroEntrada.readObject();
			 
			brokersTemp = readObject;
            ficheroEntrada.close();
            
            return brokersTemp;
 
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: El fichero no existe. ");
        } catch (IOException ioe) {
            System.out.println("Error: Fallo en la escritura en el fichero. ");
        } catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return brokersTemp;
	}

}
