/*
����ȣ ����� ���ϴ� MP3�÷��̾ �����غ���
�ڽİ�ü�� �Ͽ���, ������ �����ϱ� ���� Ŭ������ �ٷ� �߻�Ŭ�����ε�,
�߻�Ŭ������ Ŭ�����̹Ƿ�, ���߻�� ������ ��Ģ�� ���� �����θ� ������ ����.
*/
package music;
public class MP3 extends MusicPlayer implements Roller,Jet{
	int price;
	String color;
	
	public void play(){
	}
	public void setVolume(){
		System.out.println("��������");
	}
	public void readMP3(){
		System.out.println("mp3");
	}
	public void roll(){
	}
	public void fly(){
	}
}
