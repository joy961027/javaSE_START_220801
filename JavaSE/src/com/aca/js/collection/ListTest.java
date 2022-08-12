package com.aca.js.collection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ListTest extends JFrame{
	//앞으로 생성될 버튼을 담을 배열
	//JButton[]bt= new JButton[20];유저가 동적으로 버튼을 생성하므로 배열로는 해결 못함.
	//해결책 배열의 특징을 모두 갖고 있으면서 그 크기가 유여한 list로 ㅓ리해보자!
	ArrayList<JButton> jbtarr= new ArrayList<JButton>();
	//멤버변수는 사실, 부품이 온다. 자바에서는 부품관계를 has a 관계라 한다.
	JButton bt_create,bt_color;
	JPanel p; //그냥 비어있는 컴포넌트 div와 비슷	
	
	public ListTest() {
		bt_create = new JButton("버튼 생성");
		bt_color = new JButton("색상 적용");
		p = new JPanel();
		//this란 레퍼런스 변수 객체의 인스턴스가 자기 자신을 가리키는 변수 용도
		//현실에서는 "나"
		setLayout(new FlowLayout());
		add(bt_create);
		add(bt_color);
		add(p);
		p.setBackground(Color.YELLOW);
		p.setPreferredSize(new Dimension(300,450));
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // public static final
		bt_create.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				createBtn();
			}
		});
		
		bt_color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setColor();
			}
		});
	}
	
	public void createBtn() {
		
		JButton bt = new  JButton("버튼1");
		jbtarr.add(bt);
		p.add(bt);//버튼을 패널에 부착 (패널은 디폴트가 flowlayout)
		//화면 갱신 요청
		p.updateUI();
	}
	public void setColor() {
		//동적으로 생성된 모든 ~버튼 의 배경색을 blue색으로 변경해보자.
		
		/*
		for (int i = 0; i < jbtarr.size(); i++) {
			jbtarr.get(i).setBackground(Color.BLUE);
		}
		*/
		//컬렉션 프레임웍을 다룰때 사용가능한 improved  for문 
		for(JButton bt:jbtarr) {
			bt.setBackground(Color.BLUE);
		}
	}
	
	public static void main(String[] args) {
		new ListTest();
	}

}
