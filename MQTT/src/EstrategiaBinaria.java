import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EstrategiaBinaria implements EstrategiaArchivo{

	@Override
	public void guardar(ArrayList<Broker> b) throws IOException, FileNotFoundException {
		String fichero = "Brokers.dat";
		 
		ObjectOutputStream ficheroSalida = new ObjectOutputStream(new FileOutputStream(fichero));
        ficheroSalida.writeObject(b);
        ficheroSalida.flush();
        ficheroSalida.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Broker> leer() throws IOException, FileNotFoundException {
		String fichero = "Brokers.dat";
		ArrayList<Broker> brokersTemp = new ArrayList<>();
		
        ObjectInputStream ficheroEntrada = new ObjectInputStream(new FileInputStream(fichero));
        ArrayList<Broker> readObject;
		
        try {
        	readObject = (ArrayList <Broker>) ficheroEntrada.readObject();
			 
    		brokersTemp = readObject;
            ficheroEntrada.close();
        
        } catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
        
        return brokersTemp;
	}

}
