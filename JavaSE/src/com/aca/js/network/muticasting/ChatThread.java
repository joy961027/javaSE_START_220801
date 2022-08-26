package com.aca.js.network.muticasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

/*
 * 대화를 담당할 객체, 이객체는 독립수행되어야하므로, 쓰레드로 정의한다
 */
public class ChatThread extends Thread{
	BufferedReader br;
	BufferedWriter bw;
	Socket socket;
	GUIMutiServer guiUniserver;
	boolean flag=true; //청취를 할지 말지 결정 짓는 논리값
	public ChatThread() {

	}
	public ChatThread(Socket socket,GUIMutiServer guiUniserver) {
		this.socket=socket;
		this.guiUniserver = guiUniserver;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void listen() {
		int i=0;
		String msg=null;
		try {
			msg = br.readLine();
			//접속한 모든자에게 메시지보내는것을 broadcasting
			for(i=0; i<guiUniserver.vec.size();i++) {				
				guiUniserver.vec.get(i).send(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
			//readLine 메서드가 실패한경우(상대방이 소켓을 끊고 나가는 경우)
			//vector에서 제거해주고 현재  쓰레드가 사망할수 있도록 무한루프를 꺼줌
			guiUniserver.vec.remove(i);
			flag=false;
		}
		guiUniserver.area.append(msg+"\n");
	}
	public void send(String msg) {
		try {
			//나와 연결된 클라이언트에게만 보내지말고 현재 서버에 접속한 모든 클라이언틍의 chatThread의 출력스트림을
			//이용해보
			bw.write(msg+"\n");
			bw.flush();
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
