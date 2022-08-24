package com.aca.js.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//문자기반 스트림을 이용하여, 메모장 편집기를 제작해본다
public class MemoApp extends JFrame {
	JMenu m_file, m_edit, m_style, m_view, m_help;
	JMenuItem item_open, item_save, item_exit;
	JMenuBar bar; // 눈에 보이지는 않지만, 메뉴들을 감싸 안는 컨테이너
	JTextArea content; // 편집기 영역
	JScrollPane scroll; // 편집기에 적용할 스크롤
	JFileChooser chooser;//파일탐색기
	FileReader fr; // 파일을 대상으로 한 문자기반의 스트림
	BufferedReader br;				//우리가 문자기반을 선택한 이유 ? 복사가 아니라, 메모리에 올라온 데이터가 한글이 깨지면 안되므로

	public MemoApp() {
		super("메모장");// 부모의 초기화 보다 시급한 일은 없음 자격증 시험(생물학적으로 부모의 초기화가 자식의 초기화 보다 앞서야 한다)
		// 따라서 부모의 생성자 호출은 자식의 생성자내에서 호출시 그 무엇보다 최우선해야한다.
		m_file = new JMenu("파일");
		m_edit = new JMenu("편집");
		m_style = new JMenu("서식");
		m_view = new JMenu("보기");
		m_help = new JMenu("도움말");
		item_open = new JMenuItem("열기");
		item_save = new JMenuItem("저장");
		item_exit = new JMenuItem("종료");
		m_file.add(item_open);
		m_file.add(item_save);
		m_file.addSeparator();
		m_file.add(item_exit);
		bar = new JMenuBar();
		content = new JTextArea();
		scroll = new JScrollPane(content);
		chooser = new JFileChooser("D:/javase_workspace/JavaSE/data");
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);

		// bar는 우리가 원하는 곳에 붙일수 있는 것이 아니라, 언제나 윈도우창 상단에 고정
		this.setJMenuBar(bar);// 정해져있음
		add(scroll);

		setSize(800, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		item_open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});


	}

	public void openFile() {
		
		int result =chooser.showOpenDialog(this);
		//상수는 직관성이 있는 문자를 이용하므로, 개발시 이해하기 좋음, 만약 상수 쓰기 싫으면? 숫자 이용
		if(result==JFileChooser.APPROVE_OPTION) {
			content.setText("");
			File file = chooser.getSelectedFile();
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				//한 문자 읽기
				
				while(br.ready()) {
					//wrapper클래스란? 자바의 모든 기본 자료형마다 1:1 대응되는 클래스를 의미하며
					//주용도는 기본자료형과 객체 자료형간 형변환 지원 하거나, 자료형을 이용한 다양한 처리시 활용..
					//실수: float(4)<double(8) -Double
					//정수: byte(1) -Byte <short(2)- Short< int(4) -Integer <long(8) -Long
					//문자 :char - Character
					//논리 : boolean -Boolean
					content.append(br.readLine()+"\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(br!=null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
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

	}

	public static void main(String[] args) {
		new MemoApp();
	}
}
