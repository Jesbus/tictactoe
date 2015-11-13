package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;

import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Game extends JFrame implements ActionListener{
	JPanel content ;
	private JTextField textField;
	private ArrayList<JButton> buttons=new ArrayList<JButton>();
	private final JTextArea txtrHr;
	private Socket socket;
	private Thread networkThread = new Thread(new Runnable(){public void run(){
		try
		{
			InputStream is = socket.getInputStream();
			
			while (true) // Keep reading from the input stream forever
			{
				byte[] lengthByteBuffer = new byte[1]; // Each packet starts with one length byte
				
				while (is.read(lengthByteBuffer, 0, 1)<=0){Thread.sleep(1);} // Wait to receive the length byte
				
				byte[] packet = new byte[lengthByteBuffer[0]]; // Allocate a buffer of the size of the length byte
				
				int position = 0; // We start writing to byte[] packet at packet[0]
				
				while (true)
				{
					Thread.sleep(1);
					int amountReceived = is.read(packet, position, lengthByteBuffer[0]-position); // Read from the socket
					
					if (amountReceived<=0) continue; // If we didn't receive anything, continue this loop until we do
					
					position += amountReceived; // Advance the buffer position by the amount we received
					
					if (position==amountReceived)
					{
						// The full packet has been read into  byte[] packet, so quit the reading loop
						break;
					}
					else if (position>amountReceived)
					{
						// TODO this should never happen
					}
				}
				
				// TODO parse the actual packet data
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			// TODO
		}
	}});
	
	public Game(String user,boolean server, Socket socket) {
		this.socket = socket;
		networkThread.start();
		setResizable(false);
		setTitle("TicTacToe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,455,305);
		content= new JPanel();
		setContentPane(content);
		content.setLayout(null);
		
		
		JButton button = new JButton("");
		button.setBounds(12, 12, 117, 55);
		content.add(button);
		buttons.add(button);
		
		
		JButton button_1 = new JButton("");
		button_1.setBounds(169, 12, 117, 55);
		content.add(button_1);
		buttons.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setBounds(326, 12, 117, 55);
		content.add(button_2);
		buttons.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(12, 79, 117, 55);
		content.add(button_3);
		buttons.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setBounds(169, 79, 117, 55);
		content.add(button_4);
		buttons.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.setBounds(326, 79, 117, 55);
		content.add(button_5);
		buttons.add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.setBounds(12, 146, 117, 55);
		content.add(button_6);
		buttons.add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.setBounds(169, 146, 117, 55);
		content.add(button_7);
		buttons.add(button_7);
		
		JButton button_8 = new JButton("");
		button_8.setBounds(326, 146, 117, 55);
		content.add(button_8);
		buttons.add(button_8);

		
		
		
		txtrHr = new JTextArea(2,20);
		txtrHr.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtrHr.setEditable(false);
		txtrHr.setBounds(12, 212, 432, 50);
		content.add(txtrHr);
		JScrollPane ps = new JScrollPane(txtrHr);
		ps.setSize(432, 50);
		ps.setLocation(12, 212);
		ps.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		content.add(ps);
		
		
		
		textField = new JTextField();
		textField.setBounds(12, 274, 431, 19);
		content.add(textField);
		textField.setColumns(10);
		for(JButton x : buttons)x.addActionListener(this);
	}
	boolean change =false;
	public void actionPerformed(ActionEvent e) {
		int index=buttons.indexOf(e.getSource());
		
		((JButton)e.getSource()).setEnabled(false);
		if(change){
			change=!change;
			((JButton)e.getSource()).setText("X");
		}
		else{
			change=!change;
			((JButton)e.getSource()).setText("O");
		}
		
		txtrHr.append("Button "+index+" pressed!\n");
	}
}
