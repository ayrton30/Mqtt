import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EstrategiaTexto implements EstrategiaArchivo{

	@Override
	public void guardar(ArrayList<Broker> b) {
		// TODO Auto-generated method stub
		File archivo = new File("Brokers.txt");
		
		FileOutputStream salida = null;
        ObjectOutputStream writer = null;

        try {
            archivo.createNewFile();

            salida = new FileOutputStream(archivo);
            writer = new ObjectOutputStream(salida);

            writer.writeObject(b);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (salida != null) {
                try {
                    salida.close();
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                } }
        }
	}

	@Override
	public ArrayList<Broker> leer() {
		// TODO Auto-generated method stub
		File archivo = new File("Brokers.txt");
		
		FileInputStream entrada = null;
        ObjectInputStream reader = null;

        try {
            entrada = new FileInputStream(archivo);
            reader = new ObjectInputStream(entrada);

            ArrayList<Broker> temp = new ArrayList<>();
            temp = (ArrayList<Broker>) reader.readObject(); //Leemos del archivo el array de Broker
            return temp;
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

        finally{
            if(entrada != null){
                try {
                    entrada.close();
                    if(reader != null){
                        reader.close();
                    }
                } catch (IOException ex) {
                }}
        }
		ArrayList<Broker> temp = new ArrayList<>();
		return temp;
	}
	
}