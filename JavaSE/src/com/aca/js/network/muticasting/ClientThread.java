package com.aca.js.network.muticasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/*
 * 클라이언트가 메시지를 보낼때 청취하는 것이 아니라, 언제나 서버로부터 전송되어오는 메시지를
 *무한루프로 입력받아야 하므로 별도의 쓰레드가 필요하다
 *만일 메인실행부로 무한루프를 걸어버리면, 클라이언트측 gui프로그램이 멈춘다.
 */
public class ClientThread extends Thread {
	BufferedReader br;
	BufferedWriter bw;
	Socket socket;
	GUIClient guiClient;
	boolean flag = true;

	// 이쓰레드가 태어날때 이미 대화할 준비가 되어있어햐 하므로, 생성자에서 스트림을 뽑자
	public ClientThread() {

	}

	public ClientThread(Socket socket, GUIClient guiClient) {
		this.socket = socket;
		this.guiClient = guiClient;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//서버가 나한테 보낸것
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //말하기용
	}

	// 서버로부터 전송된 데이트를 받는다!!(입력)
	public void listen() {
		String msg = null;
		try {
			msg = br.readLine();
			guiClient.area.append(msg + "\n");
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	// 출력스트림을 이용하여 문자열을 출력해본다
	public void send(String msg) {
		try {
			bw.write(msg + "\n");
			bw.flush();// 버퍼처리된 출력스트림의 경우만
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	 @Override
	public void run() {
		 while(flag) {
			 listen();
		 }
	 }

}
