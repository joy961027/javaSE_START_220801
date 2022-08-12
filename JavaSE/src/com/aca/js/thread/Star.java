package com.aca.js.thread;

//별을 출력하는 쓰레드 정의
public class Star extends Thread {
	String mark;
	public Star(String mark) {
		this.mark=mark;
		
	}
	// 쓰레드의 실행순서는 개발자가 결정짓는게 아니지만, 적어도 로직은 개발자가 작성해야하므로
	// run메서드를 재정의해놓아야한다,
	// run()메서드의 호출자는?jvm이호출
	public void run() {
		while (true) {
			System.out.println(mark);
			//while문은 속도가 너무 빠르무로 jvm에게 너무 빠른 run을 맞는다 따라서
			//지정한 시간동안 non-runnable영역으로 빼놓을수 있는  기능이 지원된다.
			//sleep(시간)
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
