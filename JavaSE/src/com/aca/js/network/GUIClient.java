package com.aca.js.network;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	Socket socket;
	BufferedWriter bw;
	BufferedReader br;
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
		
		
		//접속버튼에 리스너 연결
		bt_connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		bt_send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
		
		t_input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
		
	}
	
	public void connect() {
		try {
			socket = new Socket(ch.getSelectedItem(),port);
			if(socket!=null) {
				System.out.println("접속성공");
			}
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		t_port.getText();
	}
	
	//입력한데이터를 서버에 보내자(출력) 실행중인 프로그램에서 데이터 나가는 것이므로
	public void send() {
		try {
			bw.write(t_input.getText()+"\n");//버퍼로 모은 문장의 끝을 알려주기 위해 반드시 줄바꿈 문자를 포함해서 보내야 서버가 무한정 대기하지 않는다
			//출력스트림은 입력스트림과는 다르게 버퍼를 처리할 경우 반드시 버퍼를 비워저야 한다 
			//미국사람들은 화장실 물 내리기 
			bw.flush();
			t_input.setText("");//입력값 초기화
			String data = br.readLine();
			area.append(data+"\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		new GUIClient();
	}
}






