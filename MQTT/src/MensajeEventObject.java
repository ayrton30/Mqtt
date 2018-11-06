import java.util.EventObject;

public class MensajeEventObject extends EventObject{
	// Variable de instancia para diferencia a cada objeto de este tipo
    String mensaje;

    // Constructor parametrizado
    MensajeEventObject(Object obj, String mensaje) {
        // Se le pasa el objeto como parametro a la superclase
        super(obj);
        
        // Se guarda el identificador del objeto
        this.mensaje = mensaje;
        }

    // Método para recuperar el identificador del objeto
    String getMensaje() {
        return(mensaje);
    }
}
