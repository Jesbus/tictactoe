package GUI;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

public class LogDiag extends JFrame implements ActionListener {
	private JTextField textField;
	private JButton btnCreateGame;
	private JButton btnJoinGame;
	public LogDiag() {
		setResizable(false);
		getContentPane().setLayout(new GridLayout(6, 0, 0, 0));
		setSize(219, 201);
		setTitle("TicTacToe");
		JLabel lblUsername = new JLabel("Username");
		getContentPane().add(lblUsername);
		
		textField = new JTextField();
		getContentPane().add(textField);
		textField.setColumns(10);
		
		btnCreateGame = new JButton("Create Game");
		getContentPane().add(btnCreateGame);
		btnCreateGame.addActionListener(this);
		
		JLabel lblServerIp = new JLabel("Server Ip:");
		getContentPane().add(lblServerIp);
		
		JComboBox comboBox = new JComboBox();
		
		getContentPane().add(comboBox);
		try {
			ArrayList<InetAddress> x= getOpenGames();
			for(InetAddress i:x){
				comboBox.addItem(i.getCanonicalHostName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		btnJoinGame = new JButton("Join Game");
		getContentPane().add(btnJoinGame);
		btnJoinGame.addActionListener(this);
	}
	Net.server server = null;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnJoinGame)){
		}
		if(e.getSource().equals(btnCreateGame)){
			if (server==null) // If there is no server running...
			{
				try {
					server = new Net.server();
					btnCreateGame.setText("Cancel game"); // The button will now act as server cancel button
				} catch (IOException e1) {
					e1.printStackTrace();
					// TODO alert the user that the server cannot be hosted.
				}
			}
			else // If there is already a server running, cancel it.
			{
				server.cancel();
				server = null;
				btnCreateGame.setText("Create game");
			}
		}
	}
	
	public ArrayList <InetAddress> getOpenGames() throws IOException{
		ArrayList<InetAddress> xips = new ArrayList<InetAddress>();
		InetAddress localhost = InetAddress.getLocalHost();
		
		byte[] ip = {(byte) 192,(byte) 168,(byte) 188,(byte) 255};
		for (int i = 1; i <= 254; i++)
		{
			ip[3] = (byte)i;
			InetAddress address = InetAddress.getByAddress(ip);
			if (address.isReachable(100))
			{
//				try{
//					Socket x = new Socket(address, 2656);
					xips.add(address);
//				}
//				catch (IOException e){
//				}
			}
		}
		return xips;
	}

}
