/*
java�� c#�� ���� �Ϲ����� �������α׷�ó�� gui�� �������ش�
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
		Button button = new Button("�� ��ư");
		//text�Է�
		TextField tf = new TextField(10);//�⺻������ Ȯ���� �ʺ� 10��ũ��
		//checkbox
		Checkbox ch = new Checkbox("����");
		Checkbox ch2 = new Checkbox("���α׷���");
		
		Choice choice = new Choice();
		choice.add("red");
		choice.add("yellow");
		choice.add("green");
		choice.add("blue");
		choice.add("gray");
		//radiobox radio�� ������ ���������ʰ�, ������ üũ�ڽ��� �׷�ȭ ��Ű���.
		CheckboxGroup  chg = new CheckboxGroup();
		Checkbox c1 = new Checkbox("��",chg,false);
		Checkbox c2 = new Checkbox("��",chg,false);
		Checkbox c3 = new Checkbox("Ʈ����",chg,false);
		
		//TEXTAREA ��Ƽ���� �Է�â
		TextArea area = new TextArea(20,10); // row, col�� �ټ� ����
		


		Frame frame = new Frame();
		frame.setSize(300,400);
		//�����ӿ� ������ ��ġ����
		frame.setLayout(new FlowLayout());
		frame.add(button); //frame�� ��ư ����
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
