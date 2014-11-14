package com.qiheng.Client;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qiheng.util.CharacterUtil;

public class Client extends JFrame {
	private JPanel panel;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;

	private JTextField username;
	private JTextField hostaddress;
	private JTextField port;
	
	
	private JButton button1;
	private JButton button2;
	
	private Thread thread;
	public Client() throws HeadlessException {
		super("服务器");
		init();
	}
	private void init() {
		panel=new JPanel();
		label1=new JLabel("用户名");
		label2=new JLabel("服务器");
		label3=new JLabel("端口号");
		
		username=new JTextField(15);
		hostaddress=new JTextField(15);
		port=new JTextField(15);
		button1=new JButton("登录");
		button2=new JButton("重置");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		panel.setBorder(BorderFactory.createTitledBorder("用户登录"));
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button1ActionListener(e);
			}
		});
		
		panel.add(label1);
		panel.add(username);
		panel.add(label2);
		panel.add(hostaddress);
		panel.add(label3);
		panel.add(port);
		panel.add(button1);
		panel.add(button2);
		
		
		this.getContentPane().add(panel);
		this.setSize(250, 300);
		this.setVisible(true);
	}
	private void button1ActionListener(ActionEvent e) {

		String username=this.username.getText();
		String hostAddress=this.hostaddress.getText();
		String hostport=this.port.getText();
		int port=Integer.parseInt(hostport);
		CharacterUtil.CLIENT_NAME=username;
		CharacterUtil.SERVER_HOST=hostAddress;
		thread=new ClientConnectThread(this,hostAddress,port,username);
		thread.start();
		
	}
	public static void main(String[] args) {
		new Client();
	}
	
}
