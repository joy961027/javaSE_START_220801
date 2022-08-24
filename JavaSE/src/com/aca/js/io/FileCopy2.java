package com.aca.js.io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class FileCopy2 extends JFrame {
	JButton bt;
	JTextField t_ori, t_dest;
	JProgressBar bar;
	FileReader fis; // 문자 기반의 파일입력 스트림
	FileWriter fos; // 문자 기반의 파일출력 스트림

	public FileCopy2() {
		bt = new JButton("복사 실행");
		t_ori = new JTextField(30);
		t_dest = new JTextField(30);
		bar = new JProgressBar();
		// 스타일
		bar.setPreferredSize(new Dimension(380, 55));
		bar.setBackground(Color.CYAN);

		// 조립
		setLayout(new FlowLayout());
		add(bt);
		add(t_ori);
		add(t_dest);
		add(bar);
		// 윈도우창 보이기
		setSize(400, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				copy();

			}
		});

	}

	public void copy() {
		// JOptionPane.showMessageDialog(this, "눌렀어");
		try {
			String oriPath = t_ori.getText();
			String destPath = t_dest.getText();
			fis = new FileReader(oriPath);  //파일을 대상으로 빨대 꽃기
			//파일을 대상으로 한 출력 스트림인 fileoutputStream 은 생성시 빈(empty) 파일을
			//생성해준다			
			fos = new FileWriter(destPath);
			int data=-1;
			while(fis.ready()) {
				data = fis.read();
				System.out.println((char)data);
				fos.write(data);
			}
			 JOptionPane.showMessageDialog(this, "복사 완료");
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "파일이 존재 하지 않습니다");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new FileCopy2();
	}

}
