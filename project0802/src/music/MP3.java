/*
박종호 사원이 원하는 MP3플레이어를 정의해본다
자식객체로 하여금, 구현을 강제하기 위한 클래스가 바로 추상클래스인데,
추상클래스는 클래스이므로, 다중상속 금지의 원칙에 따라 여러부모를 가지지 못함.
*/
package music;
public class MP3 extends MusicPlayer implements Roller,Jet{
	int price;
	String color;
	
	public void play(){
	}
	public void setVolume(){
		System.out.println("볼륨조절");
	}
	public void readMP3(){
		System.out.println("mp3");
	}
	public void roll(){
	}
	public void fly(){
	}
}
