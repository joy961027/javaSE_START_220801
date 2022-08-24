package com.aca.js.thread;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.aca.js.domain.Movie;

public class Gallery extends JFrame{
	JPanel p_controller;
	JPanel p_content;
	JButton bt_prev, bt_next, bt_auto;
	URL url; //자원의 위치를 말하는 객체
	FileReader fr; //한 문자를 읽을수 잇는 입력스트림
	BufferedReader br;	//버퍼치리된 문자기반 스트림
	List<Movie> movieList;
	BufferedImage img; // 패널이 그리게 될 이미지 객체
	int index=0;
	
	public Gallery() {
		p_controller = new JPanel();
	
		/*개발자가 컴포넌트를 그냥 사용하면, sun에서 이미 정해 놓은 그림을 이용하게 된다..
		 * 하지만, 개발자가 이 그림을 커스터마이징 할 수 있는데, 이때 오버라이드 해야 할 메서드가 바로 paint 메서드이다.
		 * */ 
		init();
		System.out.println("최종적으로 모여진 영화의 수는 " +movieList.size());
		loadImage(index);
		p_content = new JPanel() {
			//아래의 메서드를 재정의 하는 순간부터는 개발자가 그린 그림을 우선시해준다..
			//페인트 메서드의 호출시점은? 다시 그려져야 할때
			public void paint(Graphics g) {
				
				g.drawImage(img, 0, 0, 600, 500, p_controller);
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
		bt_auto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//무한루프가 걸리게 되므로, 절대 메인쓰레드는 루프에 넣엇는 안됨..
				//메인 쓰레드 대신 업무를 처리할 개발자 정의 쓰레드로 처리
				Thread thread = new Thread() {
					@Override
					public void run() {
						auto();
					}
				};
				thread.start();
			}
			
		});
		
	}
	
	//프로그램에서 사용할 데이터 가져오기
	public void init() {
		//json데이터는 파일로 존재한다 .. 따라서 실행중인 자바프로그램에서 문서 파일을 읽어야 하므로
		//지금 필요한 기술은 입렵스트림이 필요하다.
		//1) 방향 : 모든 스트림은 데이터의 처리방향에 따라 입력, 출력
		//2) 다루는 데이터:바이트기반, 문자기반, 버퍼처리 
		try {
			fr = new FileReader("D:/javase_workspace/JavaSE/data/data.json");
			JSONParser jp = new JSONParser();
			JSONObject jo = (JSONObject) jp.parse(fr);
			JSONArray marvel = (JSONArray) jo.get("marvel");
			movieList = new ArrayList<Movie>();
			for(int i=0; i<marvel.size();i++) {
				JSONObject jsonMovie = (JSONObject) marvel.get(i);
				Movie movie = new Movie();
				movie.setUrl((String) jsonMovie.get("url"));
				movie.setTitle((String) jsonMovie.get("title"));
				movieList.add(movie);
			}
			//곧 닫히게될 스트림과 죽게될 jsonArray변수를 대체할 방법
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fr!=null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void loadImage(int index) {
		Movie movie = movieList.get(index);//영화 한편을 얻는다 (제목과 url들어있음);
		try {
			URL url = new URL(movie.getUrl());
			img = ImageIO.read(url);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void nextImage() {
		if(index<movieList.size()-1) {
			index++;
			loadImage(index);
			//패널의 그림을 다시 그리는 방법(프로그래밍 적으로)
			//repaint() -> update()화면을 모두지움 -> patin()스스로 호출
			p_content.repaint();
		}else {
			JOptionPane.showMessageDialog(this, "마지막 이미지입니다.");
		}
		
	}
	
	public void prevImage() {
		if(index==0) {
			JOptionPane.showMessageDialog(this, "첫번째 이미지입니다.");
		}else {
			index--;
			loadImage(index);
			//패널의 그림을 다시 그리는 방법(프로그래밍 적으로)
			//repaint() -> update()화면을 모두지움 -> paint()스스로 호출
			p_content.repaint();
		}
	}
	
	public void auto() {
		
		while(true) {
			nextImage();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		new Gallery();
	}

}
