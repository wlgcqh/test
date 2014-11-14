package com.qiheng.Server;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qiheng.util.CharacterUtil;

public class MainServer extends JFrame {
	private JPanel panel1;
	private JPanel panel2;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextArea textarea;
	private JTextField textfield;
	private JScrollPane scrollPane;
	private JButton button;
	
	
	
	private Map<String,String> map=new HashMap<String,String>();//用户名与端口号的映射
	
	private Thread thread;
	
	

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public MainServer() throws HeadlessException {
		super("服务器");
		this.init();

	}

	private void init() {
		panel1 = new JPanel();
		panel2 = new JPanel();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		textarea = new JTextArea();
		textfield = new JTextField(10);
		scrollPane = new JScrollPane();
		button = new JButton();

		panel1.setBorder(BorderFactory.createTitledBorder("服务器信息"));
		panel2.setBorder(BorderFactory.createTitledBorder("在线用户列表"));
		label1.setText("服务器状态");
		label2.setText("停止");
		label3.setText("端口号");
		button.setText("启动服务器");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				check();
			}
		});
		textarea.setEditable(false);
		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);
		panel1.add(textfield);
		panel1.add(button);
		textarea.setColumns(30);
		textarea.setRows(20);
		this.scrollPane.setViewportView(textarea);
		panel2.add(scrollPane);
		this.getContentPane().add(panel1, BorderLayout.NORTH);
		this.getContentPane().add(panel2, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new MainServer();
	}

	private void check() {
		String port = textfield.getText();
		if (!CharacterUtil.isEmpty(port)) {
			JOptionPane.showMessageDialog(this, "端口号不能为空！", "警告",
					JOptionPane.WARNING_MESSAGE);
			textfield.requestFocus();
			return;
		}
		if (!CharacterUtil.isNumber(port)) {
			JOptionPane.showMessageDialog(this, "端口号必须为数字！", "警告",
					JOptionPane.WARNING_MESSAGE);
			textfield.setText("");
			textfield.requestFocus();
			
			return;
		}
		if (!CharacterUtil.isPort(port)) {
			JOptionPane.showMessageDialog(this, "端口号要在1024与65535之间！", "警告",
					JOptionPane.WARNING_MESSAGE);
			textfield.setText("");
			textfield.requestFocus();
			return;
			
		}
		int hostport=Integer.parseInt(port);
		System.out.println(hostport);
		thread=new ConnectThread(this, "ConnectThread", hostport);
		thread.start();
		this.button.setText("运行中");
		this.label3.setText("运行中");
		this.textfield.setEnabled(false);
		this.button.setEnabled(false);
		
		
	}
	public void setUserList(){
		this.textarea.setText("");
		for(Iterator<String> iter=map.keySet().iterator();iter.hasNext();){
			String key=(String) iter.next();
			this.textarea.append(key+"\n");
		}
	}

	
}
