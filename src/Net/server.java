package Net;

import java.io.IOException;
import java.net.ServerSocket;

public class server {
	private ServerSocket thissock=null;
	
	public server() throws IOException{
		thissock= new ServerSocket(2656);
		
	}
}
