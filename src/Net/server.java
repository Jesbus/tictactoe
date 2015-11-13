package Net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	private ServerSocket thissock=null;
	
	public server() throws IOException{
		thissock= new ServerSocket(2656);
		while(true)
		{
			Socket s = thissock.accept();
		}
	}
}
