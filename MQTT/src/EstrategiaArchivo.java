import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

interface EstrategiaArchivo {
	void guardar(ArrayList<Broker> b) throws IOException, FileNotFoundException;
	ArrayList<Broker> leer() throws IOException, FileNotFoundException;
}

