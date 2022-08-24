package com.aca.js.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/*
 * 인터넷 상의 이미지를 나의 pc로 저장하기(저작권 주의)
 */
public class ImageCollector {
	FileInputStream fis;
	FileOutputStream fos;
	InputStream is;
	public ImageCollector()  {
		
		try {
			URL url = new URL("https://images-na.ssl-images-amazon.com/images/I/91qvAndeVYL._RI_.jpg");
			is = url.openStream();
			fos = new FileOutputStream("D:/javase_workspace/JavaSE/data/아이언맨.jpg");
			int data=-1;
			while(true) {
				data = is.read();
				if(data==-1)break;
				fos.write(data);
			}
			System.out.println("수집완료");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
	}
	public static void main(String[] args) {
		//실행중인 프로그램으로 데이터를 읽어와서, 원하는 위치에 빈파일을 만든후, 데이터를 채워넣기
		new ImageCollector();
	}
}
