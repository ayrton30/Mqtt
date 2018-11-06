import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

interface EstrategiaArchivo {
	void guardar(ArrayList<Broker> b, String path) throws IOException, FileNotFoundException;
	ArrayList<Broker> leer(String path) throws IOException, FileNotFoundException;
}

