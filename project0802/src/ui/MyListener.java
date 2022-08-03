package ui;
/*
사용자가 gui상에서 어떠한 행동을 했을때, 제일먼저 os가 감지하여,jvm에게 전달하고
jvm은 이 이벤트정보를 이용하여 이벤트 객체의 인스턴스를 생성한다..
이때 이 인스턴스는 개발자에게 전달될수 있는데, 이 인스턴스를 개발자가 전달 받으려면
전담 리스너를 활용해야한다. 하지만, 이 리스너는 몸체가 없는 불완전한 객체로 되어있다..
그 이유는, 몸체를 완성시킬 의무를 개발자에게 부여하기 위함이다..
*/

/*
사용자가 키보드 관련된 이벤트를 발생 시키면 os가 다시 jvm에게 전달하고
이정보를 받은 jvm이 이때 메모리에 keyEvent 인스턴스를 생성한다.
이 정보는 개발자에게 전달되어야하므로 개발자는 리스너객체를 이요하여 전달받을수 있는데
이때 리스너가 인터페이스로 제공되는 이유는 이 인터페이스를 완성할 의무를 개발자에 부여했기 때문이다.
*/
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class  MyListener implements ActionListener,KeyListener{
								/* is a */	
	//우리가 상속받은 actionListener 인터페이스는 불완전한 객체이므로.
	//MyListener 클래스는 이 불완점하을 오나전하게 할 구현의무를 가지게 된다..
	//특히 ActionListener 인터페이스는 actionPerformed()라는 추상메서드를 가지고 있으므로 개발자가 반드시 완성시켜줘야함
	public void actionPerformed(ActionEvent e) {
		System.out.println("버튼누름");
	}
	public void keyReleased(KeyEvent e){ //키를 눌렀다 뗄때 
		System.out.println("키 땔때");
	}
	public void keyPressed(KeyEvent e){ //키를 누룰때 동작하는 메서드
		System.out.println("키 누를때");
	}
	public void keyTyped(KeyEvent e){ // 키를 입력할때
		System.out.println("키 입력");
	}

		
}
