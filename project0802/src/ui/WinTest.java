/*자바의 gui 프로그램과 관련되 awt 패키지에서 지원하는데...
awt는 플랫폼(os)에 따라서 디자인이 약간씩 틀려진다..
따라서 이 문제를 개선한 패키지가 있는데, 바로 swing이라고 한다..
swing은 기존의 awt패키지의 클래스명에서 거의다J가 접두어로 붙는다.
Button -> JButton
*/
package ui;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class  WinTest{
	//이 객체가 태어날때, 어떠한 개성을 갖고 태어날지를 결정짓는 즉 초기화를 담당하는 
	//메서드를 가리켜 생성자라함..
	//생성자는 모양새가 클래스명과 일치함... 반환형은?? 없음!! 만약 주게되면 일반메서드로 취급하여
	//초기화 업무를 수행할 수 없다.(에러도 나지 않아 골치임)
	JFrame frame; //Swing에서 지원하는 프레임
	JButton bt;
	JTextField t; //입력상자
	public WinTest(){
		frame = new JFrame();
		bt = new JButton("나버튼");
		t = new JTextField(10);//10자정도 크기
		//버튼과 리스너 연결
		bt.addActionListener(new MyListener());
		//텍스트박스와 리스너 연결
		t.addKeyListener(new MyListener());
		//GUI프로그래밍에서는 조립 전에 반드시 자식 요소들을 어떻게 배치할지 결정해야한다.
		//배치관련된 객체 포함해서 디자인과 직접적인 관련이 없는 api는 여전히 awt를 사용...
		frame.setLayout(new FlowLayout());

		//조립
		frame.add(bt);
		frame.add(t);
		//프레임 보이게처리, 크기
		frame.setVisible(true);
		frame.setSize(300,400);
		

	}
}
