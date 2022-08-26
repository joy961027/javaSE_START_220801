package com.aca.js.network.unicasting;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * 텔넷으로는 영문의 명령어만 지원하므로 대화를 나누기에는 한계가 있음
 * 따라서 대화용 프로그램을 자바 기반으로 제작하자
 */
public class GUIClient extends JFrame{
	Choice ch; //html의 select박스와 동일
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	JButton bt_send;
	int port=9999;
	Socket socket; //통신용 소켓 데이터를 주고 받을수 있음
	BufferedReader br;
	BufferedWriter bw;
	
	public GUIClient() {
		ch = new Choice();
		t_port  = new JTextField(Integer.toString(port),6);
		bt_connect = new JButton("접속");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input  = new JTextField(15);
		bt_send = new JButton("전송");
		for(int i=13; i<=136; i++) {
			ch.add("192.168.25."+i);
			if(i==136) {//내아이피인 경우
				ch.select("192.168.25."+i);
			}
		}
		
		scroll.setPreferredSize(new Dimension(280,270));
		area.setBackground(Color.YELLOW);
		
		setLayout(new FlowLayout());
		add(ch);
		add(t_port);
		add(bt_connect);
		add(scroll);
		add(t_input);
		add(bt_send);

		//윈도우 설정
		setVisible(true);
		setBounds(200, 100, 300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
		bt_connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		//메시지 입력 텍스트 필드에 리스너 연결
		t_input.addKeyListener(new KeyAdapter() {
		
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					//자바에서는 상수가 주로, 사람이 이해하기 힘든 데이터의 경우 의미를 부여한 즉 직관성을 부여한
					//단어를 이용하여 상수를 정의함.
					send();
					listen();
				}
			}
		});
		
	}
	
	
	
	public void connect() {
		try {
			socket = new Socket(ch.getSelectedItem(),Integer.parseInt(t_port.getText()));
			
			//접속이 성공되었다면, 이 시점 부터는 대화가 가능해야하므로 입출력 스트리믕 socket 으로 뽑아내자
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//서버가 나한테 보낸것
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //말하기용
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//서버로부터 전송된 데이트를 받는다!!(입력)
	public void listen() {
		String msg=null;
		try {
			msg =br.readLine();
			area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//출력스트림을 이용하여 문자열을 출력해본다
	public void send() {
		try {
			String msg = t_input.getText();
			bw.write(msg+"\n");
			bw.flush();//버퍼처리된 출력스트림의 경우만
			t_input.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		new GUIClient();
	}
}






