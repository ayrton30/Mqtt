import java.util.ArrayList;

interface EstrategiaArchivo {
	void guardar(ArrayList<Broker> b);
	ArrayList<Broker> leer();
}

