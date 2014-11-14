package com.qiheng.Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import com.qiheng.util.CharacterUtil;

public class ClientConnectThread extends Thread {

	private JFrame frame;
	private String hostAddress;
	private int port;
	private String username;

	public ClientConnectThread(JFrame frame, String hostAddress, int port,
			String username) {
		super();
		this.frame = frame;
		this.hostAddress = hostAddress;
		this.port = port;
		this.username = username;
	}

	@Override
	public void run() {

		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;

		InetAddress host;
		try {
			host = InetAddress.getByName(hostAddress);
			socket = new Socket(host, port);
			in = socket.getInputStream();
			out = socket.getOutputStream();

			int randomPort1 = CharacterUtil.RANDOMPORT1;
			int randomPort2 = CharacterUtil.RANDOMPORT2;

			InetAddress address = InetAddress.getLocalHost();
			String clientAddress = address.toString();
			int l = clientAddress.indexOf("/");
			clientAddress = clientAddress.substring(l + 1);
			String info = username + "@@@" + randomPort1 + "_" + randomPort2
					+ "_" + clientAddress;
			out.write(info.getBytes());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
