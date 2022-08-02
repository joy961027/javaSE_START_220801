/*
java언어도 c#과 같은 일반적인 응용프로그램처럼 gui를 지원해준다
*/
package gui;

import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.CheckboxGroup;
import java.awt.TextArea;


public class ChatMain{
	public static void main(String[] args) {
		//button 
		Button button = new Button("나 버튼");
		//text입력
		TextField tf = new TextField(10);//기본적으로 확보할 너비 10자크기
		//checkbox
		Checkbox ch = new Checkbox("게임");
		Checkbox ch2 = new Checkbox("프로그래밍");
		
		Choice choice = new Choice();
		choice.add("red");
		choice.add("yellow");
		choice.add("green");
		choice.add("blue");
		choice.add("gray");
		//radiobox radio는 별도로 지원하지않고, 기존의 체크박스를 그룹화 시키면됨.
		CheckboxGroup  chg = new CheckboxGroup();
		Checkbox c1 = new Checkbox("남",chg,false);
		Checkbox c2 = new Checkbox("여",chg,false);
		Checkbox c3 = new Checkbox("트랜스",chg,false);
		
		//TEXTAREA 멀티라인 입력창
		TextArea area = new TextArea(20,10); // row, col을 줄수 있음
		


		Frame frame = new Frame();
		frame.setSize(300,400);
		//프레임에 적용할 배치관리
		frame.setLayout(new FlowLayout());
		frame.add(button); //frame에 버튼 부착
		frame.add(tf);
		frame.add(ch);
		frame.add(ch2);
		frame.add(choice);
		frame.add(c1);
		frame.add(c2);
		frame.add(c3);
		frame.add(area);
		frame.setVisible(true);
	}
}
