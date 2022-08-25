package com.aca.js.network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIEchoServer extends JFrame {
	JTextField t_port;
	JButton bt_start;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server;//접속자 감지용 소켓, (대화용 아님)
	Thread serverThread;//서버를 가동하고, 접속자를 감지하는 용도의 쓰레드
						//필요이유? accept()메서드는 접속자가 발생할때까지 대기상태에 빠지므로..
	public GUIEchoServer() {
		t_port = new JTextField("9999",12);
		bt_start = new JButton("서버가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		scroll.setPreferredSize(new Dimension(280,320));
		area.setBackground(Color.CYAN);
		

		
		setLayout(new FlowLayout());
		add(t_port);
		add(bt_start);
		add(scroll);
		
		setVisible(true);
		setBounds(500, 100, 300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//쓰레드를 별도의 .java로 만들어도 되지만, 귀찮으니 현재 클래스내에서 처리하자
		serverThread = new Thread() {
			@Override
			public void run() {
				startServer();
			}	
		};
		
		bt_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				serverThread.start();//개발자 주도가 아닌 jvm에 실행을 맡긴다.
				
			}
		});
		

	}
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버가동 및 접속자 청취중 ...\n");
			Socket socket =server.accept();//접속가 동시에 네트워크 정보 및 스트림을 제공해주는 소켓을 반환한다
			area.append("접속자발견\n");
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip+"님 접속\n");
			//소켓으로부터 스트림을 뽑아서, 데이터를 주고 받아보자
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//듣기용
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));//말하기용
			String data = null;
			while(true) {
				data = br.readLine(); //한줄 읽기 (한줄이 들어올때 \n딸려옴, 그래서 구분할줄 아는 것이다)
				area.append(data+"\n");
				
				bw.write(data+"\n");
				bw.flush();
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new GUIEchoServer();
	}
}
