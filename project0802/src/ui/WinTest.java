/*�ڹ��� gui ���α׷��� ���õ� awt ��Ű������ �����ϴµ�...
awt�� �÷���(os)�� ���� �������� �ణ�� Ʋ������..
���� �� ������ ������ ��Ű���� �ִµ�, �ٷ� swing�̶�� �Ѵ�..
swing�� ������ awt��Ű���� Ŭ�������� ���Ǵ�J�� ���ξ�� �ٴ´�.
Button -> JButton
*/
package ui;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class  WinTest{
	//�� ��ü�� �¾��, ��� ������ ���� �¾���� �������� �� �ʱ�ȭ�� ����ϴ� 
	//�޼��带 ������ �����ڶ���..
	//�����ڴ� ������ Ŭ������� ��ġ��... ��ȯ����?? ����!! ���� �ְԵǸ� �Ϲݸ޼���� ����Ͽ�
	//�ʱ�ȭ ������ ������ �� ����.(������ ���� �ʾ� ��ġ��)
	JFrame frame; //Swing���� �����ϴ� ������
	JButton bt;
	JTextField t; //�Է»���
	public WinTest(){
		frame = new JFrame();
		bt = new JButton("����ư");
		t = new JTextField(10);//10������ ũ��
		//��ư�� ������ ����
		bt.addActionListener(new MyListener());
		//�ؽ�Ʈ�ڽ��� ������ ����
		t.addKeyListener(new MyListener());
		//GUI���α׷��ֿ����� ���� ���� �ݵ�� �ڽ� ��ҵ��� ��� ��ġ���� �����ؾ��Ѵ�.
		//��ġ���õ� ��ü �����ؼ� �����ΰ� �������� ������ ���� api�� ������ awt�� ���...
		frame.setLayout(new FlowLayout());

		//����
		frame.add(bt);
		frame.add(t);
		//������ ���̰�ó��, ũ��
		frame.setVisible(true);
		frame.setSize(300,400);
		

	}
}
