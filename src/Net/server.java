package Net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Main.main;

public class server {
	private ServerSocket thissock=null;
	public void cancel()
	{
		try
		{
		thissock.close();
		}
		catch (Exception e)
		{
			
		}
	}
	public server() throws IOException{
		thissock= new ServerSocket(2656);
		while(true)
		{
			try
			{
				Socket s = thissock.accept();
				main.startGameFromSocket(s);
				break;
			}
			catch(Exception e) // if there is no accept, try again
			{
				continue;
			}
		}
	}
}
