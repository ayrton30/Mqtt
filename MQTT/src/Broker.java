import java.io.Serializable;

public class Broker implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String host;
	private int port;
	
	public Broker(String h, int p) {
		setHost(h);
		setPort(p);
	}
	
	public Broker() {
		setHost("");
		setPort(0);
	}
	
	public void setHost(String valor) {
		this.host = valor;
	}
	
	public void setPort(int valor) {
		if(valor > 0) {
			this.port = valor;	
		}
		else {
			this.port = 0;
		}
	}
	
	public String getHost() {return this.host;}
	public int getPort() {return this.port;}
	
	
	@Override
    public String toString() {
        return (this.getHost() + ":" + String.valueOf(this.getPort()));
    }
}