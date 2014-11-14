package com.qiheng.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import javax.swing.JFrame;

import com.qiheng.util.CharacterUtil;

public class ConnectThread extends Thread {
	private JFrame frame;
	private ServerSocket serversocket;
	private Socket socket;
	private InputStream in;
	private OutputStream out;

	public ConnectThread(JFrame frame, String threadName, int port) {
		this.frame = frame;
		this.setName(threadName);

		try {
			serversocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.socket = this.serversocket.accept();
				this.in = this.socket.getInputStream();
				this.out = this.socket.getOutputStream();

				byte[] buffer = new byte[1024];
				int length = 0;
				length = this.in.read(buffer);
				String info = new String(buffer, 0, length);
				System.out.println(info);
				int index = info.lastIndexOf("@@@");
				String username = info.substring(0, index);
				System.out.println(username);
				int lastindex = info.lastIndexOf("@");

				String clientport = info.substring(lastindex + 1);
				System.out.println(clientport);

				MainServer server = (MainServer) this.frame;
				Map<String, String> map = server.getMap();

				if (CharacterUtil.isDuplicated(map, username)) {

					String error = CharacterUtil.ERROR;
					out.write(error.getBytes());
					in.close();
					out.close();

					socket.close();
					

				} else {
					String success = CharacterUtil.SUCCESS;
					String info2 = success + "@@@" + CharacterUtil.PORT1 + "_"
							+ CharacterUtil.PORT2;

					map.put(username, clientport);
					server.setUserList();

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
