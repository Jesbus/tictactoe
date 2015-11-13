package Main;

import java.net.Socket;

import GUI.*;

public class main {

	static LogDiag logdiag = null;
	static Game gameWindow = null;
	public static void main(String[] args) {
		logdiag = new LogDiag();
		logdiag.show();
	}
	public static void startGameFromSocket(Socket socket)
	{
		logdiag.hide();
		gameWindow = new Game("TODO", false, socket);
		gameWindow.show();
	}
}
