package com.aca.js.thread;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Gallery extends JFrame{
	JPanel p_controller;
	JPanel p_content;
	JButton bt_prev, bt_next, bt_auto;
	Image image; //자바에서 이미지를 표현한 객체이고, 이미지 인스턴스를 얻는 방법은 상당히 다양하다.
	URL url; //자원의 위치를 말하는 객체
	int n;
	public Gallery() {
		p_controller = new JPanel();
	
		/*개발자가 컴포넌트를 그냥 사용하면, sun에서 이미 정해 놓은 그림을 이용하게 된다..
		 * 하지만, 개발자가 이 그림을 커스터마이징 할 수 있는데, 이때 오버라이드 해야 할 메서드가 바로 paint 메서드이다.
		 * */ 
		loadImage(this.n);
		p_content = new JPanel() {
			//아래의 메서드를 재정의 하는 순간부터는 개발자가 그린 그림을 우선시해준다..
			//페인트 메서드의 호출시점은? 다시 그려져야 할때
			public void paint(Graphics g) {
				
				g.drawImage(image, 0, 0, 600, 500, p_controller);
			}
		};
		bt_prev  = new JButton("이전");
		bt_next = new JButton("다음");
		bt_auto =  new JButton("auto");
		
		
		
		//스타일 적용;
		p_controller.setPreferredSize(new Dimension(600,50));
		p_controller.setBackground(Color.CYAN);
		p_content.setPreferredSize(new Dimension(600,500));
		p_content.setBackground(Color.YELLOW);
		//배치관리자 적용
		setLayout(new FlowLayout());
	
		//조립
		add(p_controller);
		add(p_content);
		p_controller.add(bt_prev);
		p_controller.add(bt_next);
		p_controller.add(bt_auto);
		//윈도우 속성
		setLocationRelativeTo(null); //화면 가운데로 윈도우 창을 띄운다
		setSize(600,550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		bt_next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nextImage();
				
			}
		});
		
		
		bt_prev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prevImage();
				
			}
		});
		
	}
	public void loadImage(int n) {
		//json로컬 파일로부터 이미지 정보를 얻어와 image객체 생성하기
		//data.json 이 패키지경로에 잇을때 파일을 접근하는 방법을 먼저 알아보자
		
		File file = new File("D:\\javase_workspace\\JavaSE\\data/data.json");
		FileReader fr = null;
		try {
			fr = new FileReader(file); 
			JSONParser jp = new JSONParser();
			//string 문서로 존재하던 json파일을 읽어들여, json객체화 시킨것임
			JSONObject jo=(JSONObject) jp.parse(fr);
			JSONArray ja = (JSONArray)jo.get("marvel");
			JSONObject moviejson =(JSONObject) ja.get(n);
			url = new URL((String)moviejson.get("url"));
			image = ImageIO.read(url);
		} catch (IOException  | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fr!=null)
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
		
	}
	public void nextImage() {
		n++;
		loadImage(n);
		p_content.updateUI();
	}
	
	public void prevImage() {
		n--;
		loadImage(n);
		p_content.updateUI();
	}
	
	
	
	
	public static void main(String[] args) {
		new Gallery();
	}

}
