package com.aca.day0801.main;
public class Arrow {
	private	int y;
	private String str;
	private int velX;
	
	public Arrow(int y, String str, int velX){
		this.y =y;
		this.str= str;
		this.velX = velX;
	}

	public void move(){
		this.y+= this.velX;
	
	}

	public static void main(String[] args){
		Arrow a1 = new Arrow(150,"♥",10);
		a1.move();
		System.out.println("화살날린다.");
	
	}



}
