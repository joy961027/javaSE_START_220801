package com.aca.js.io;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 1) 방향 - 입렵, 출력
 * 2) 데이터 처리방법 - 바이트 기반 : 1byte씩 처리하므로 영문이 아닌 2byte로 구성된 문자는 깨져 보임
 * 					문자기반 : 2byte를 묶어서 문자로 인식하는 스트림(한글 등 비영어권 뿐만 아니라 전세계 모든 문자깨지지 않음)
 * 							~~~Reader(문자기반 입력), ~~~~~Writer(문자기반 출력스트림)
 *					버퍼기반 : 데이터를 효율적으로 입출력하기 위한 스트림..
 * 
 * */

//로컬상의 이미지를 실행중인 프로그램을 읽어보기

public class ImageReader {
	FileInputStream fis; //파일을 대상으로 데이터를 읽어오는 바이트기반 스트림
	FileReader fr;
	BufferedReader br;
	public ImageReader() {
		try {
			//대상자원에 빨때 꽂기
			fis = new FileInputStream("D:/javase_workspace/JavaSE/data/memo.txt");
			fr = new FileReader("D:/javase_workspace/JavaSE/data/memo.txt");
			br = new BufferedReader(fr);
			System.out.println("스트림 생성 성공");
			
			int data=0;
			//연결된 스트림을 이용하여 1byte를 읽어보자
			while(data!=-1) {
				data=br.read();
				System.out.println(data);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		ImageReader ir = new ImageReader();
	}
}
