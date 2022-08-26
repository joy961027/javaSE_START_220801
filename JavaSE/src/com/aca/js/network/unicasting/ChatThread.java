package com.aca.js.network.unicasting;

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
	GUIUniServer guiUniserver;
	boolean flag=true; //청취를 할지 말지 결정 짓는 논리값
	public ChatThread() {

	}
	public ChatThread(Socket socket,GUIUniServer guiUniserver) {
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

		String msg=null;
		try {
			msg = br.readLine();
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		guiUniserver.area.append(msg+"\n");
	}
	public void send(String msg) {
		try {
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
