package ui;

//현실의 객체를 표현하고자 함이 아닌, 그냥 실행을 하기위한 클래스
//따라서 실행부가 있어야함
public class UseWin {
	WinTest winTest; 
	public UseWin(){
		winTest = new WinTest();
	}
	public static void main(String[] args) {
		new UseWin();
	}
}
