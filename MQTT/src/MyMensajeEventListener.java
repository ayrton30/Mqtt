
public class MyMensajeEventListener implements MensajeEventListener {

	@Override
	public void MensajeRecibido(MensajeEventObject args) {
		// TODO Auto-generated method stub
		
		System.out.println(args.getMensaje());
	}

}
