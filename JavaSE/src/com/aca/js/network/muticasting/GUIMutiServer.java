package com.aca.js.network.muticasting;

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
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIMutiServer extends JFrame {
	JTextField t_port;
	JButton bt_start;
	JTextArea area;
	JScrollPane scroll;
	Thread listenThread; //메인쓰레드가 accept()에 의해 대기 상태에 빠지지 않도록 별도의 쓰레드 필요
	ServerSocket server; //대화용이아니라 접속자 감시용, 즉 통신 연결용
	boolean flag =true;
	Vector<ChatThread> vec = new Vector<ChatThread>(); //어레이리스트와 기능은 동일하나 , 차이점 동기화를 지원해준다.(내부적으로 동기화 처리때문에 속도는 떨어짐)
	
	
	public GUIMutiServer() {
		t_port = new JTextField("9999",12);
		bt_start = new JButton("서버가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		listenThread = new Thread() {
			@Override
			public void run() {
					startServer();
			}
		};
		
		
		scroll.setPreferredSize(new Dimension(280,320));
		area.setBackground(Color.CYAN);
		
		setLayout(new FlowLayout());
		add(t_port);
		add(bt_start);
		add(scroll);
		
		setVisible(true);
		setBounds(500, 100, 300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//가동버튼에 리스너 연결
		bt_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listenThread.start();//쓰레드를 runnable영역으로 밀어버림 .. 개발자는 지금부터 손놓는다
			}
		});
		

	}
	
	
	
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 가동 및 접속자 기다리는중...\n");
			while(flag) {
				Socket socket = server.accept();//이메서드에 의해 접속자가 발생할때까지 무한대기 상태에 빠짐
				String ip =socket.getInetAddress().getHostAddress();
				area.append(ip+"님 접속!!\n");
				
				//대화용 쓰레드를 생성하여, 대화를 나누도록 해준다
				ChatThread chatThread = new ChatThread(socket, this);
				chatThread.start();
				
				//접속클라인트 마다 1:1 대응하는 객체인 chatThread의 주소값을 벡터에 보관해놓자
				vec.add(chatThread);
				area.append("현 재 접속자수 : "+vec.size()+"\n");
			}
		} catch (NumberFormatException | IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GUIMutiServer();
	}
}
