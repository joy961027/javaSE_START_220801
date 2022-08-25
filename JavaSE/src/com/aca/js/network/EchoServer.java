package com.aca.js.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 메아리 서버를 구축한다(채팅서버의 가장 기초적인 단계)
 */
public class EchoServer  {
	int port=9999;//1~1024 이미 os및 다른 시스템 차원에서 점유되는 포트라서 사용을 피해야한다. ex)21
	
	ServerSocket server; //네트워크를 통해 데이터를 주고받기 전에, 먼저 클라이언트와 서버와의 연결이
	//선행되어야 하는데, 이 연결을 처리해주는 전담객체를 가리켜 서버소켓이라 한다.
	//주의) 우리가 흔히 알고 잇는 대화용 소켓이 아니다!
	public EchoServer() {
		try {
			server = new ServerSocket(port);//서버 생성
			String data =null;
			while(true) {
				Socket socket = server.accept();//클라이언트가 접속하기를 기다린다.
				String ip = socket.getInetAddress().getHostAddress();
				System.out.println(ip+"님 접속");
				//Socket 을 통해 데이터를 클라이언트와 주고 받을 수 있다. 이때 개발자는 네트워크에대한 지식이
				//필요없고, 오직 데이터IO에 집중(결국 스트림 제어 집중하자)
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);//문자기반으로 변경
				BufferedReader br = new BufferedReader(isr); //문장기반
				OutputStream os= socket.getOutputStream();
				
				data = br.readLine();
				System.out.println(data);
				//os.write((char)data);
//				is.close();
//				os.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	public static void main(String[] args) {
		new EchoServer();
	}
}
