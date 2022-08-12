package com.aca.js.thread;
//프로그래스바를 증가시킬 쓰레드 정의

// 메인 쓰레드는 무한 루프나, 대기상태에 빠지게 하면 안된다. 이유는?
//메인 쓰레드는 gui프로그램을 운영하는 업무를 해야하기 때문에(이벤트 감지, 그래픽 처리)

import javax.swing.JProgressBar;

public class MyThread extends Thread {
	JProgressBar bar;
	int n = 0;
	int s;

	public MyThread(JProgressBar bar, int s) {
		this.bar = bar;
		this.s = s;
	}

	// 개발자는 쓰레드 정의시, 원하는 로직을 run을 오버라이드 해야한다
	// 추후 run메서드는 jvm에게 선택되어지며 이순간을 가리켜 running상태라 한다.
	public void run() {
			
		while (true) {
			n=n+s;
			bar.setValue(n);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
