package ui;
/*
����ڰ� gui�󿡼� ��� �ൿ�� ������, ���ϸ��� os�� �����Ͽ�,jvm���� �����ϰ�
jvm�� �� �̺�Ʈ������ �̿��Ͽ� �̺�Ʈ ��ü�� �ν��Ͻ��� �����Ѵ�..
�̶� �� �ν��Ͻ��� �����ڿ��� ���޵ɼ� �ִµ�, �� �ν��Ͻ��� �����ڰ� ���� ��������
���� �����ʸ� Ȱ���ؾ��Ѵ�. ������, �� �����ʴ� ��ü�� ���� �ҿ����� ��ü�� �Ǿ��ִ�..
�� ������, ��ü�� �ϼ���ų �ǹ��� �����ڿ��� �ο��ϱ� �����̴�..
*/

/*
����ڰ� Ű���� ���õ� �̺�Ʈ�� �߻� ��Ű�� os�� �ٽ� jvm���� �����ϰ�
�������� ���� jvm�� �̶� �޸𸮿� keyEvent �ν��Ͻ��� �����Ѵ�.
�� ������ �����ڿ��� ���޵Ǿ���ϹǷ� �����ڴ� �����ʰ�ü�� �̿��Ͽ� ���޹����� �ִµ�
�̶� �����ʰ� �������̽��� �����Ǵ� ������ �� �������̽��� �ϼ��� �ǹ��� �����ڿ� �ο��߱� �����̴�.
*/
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class  MyListener implements ActionListener,KeyListener{
								/* is a */	
	//�츮�� ��ӹ��� actionListener �������̽��� �ҿ����� ��ü�̹Ƿ�.
	//MyListener Ŭ������ �� �ҿ������� �������ϰ� �� �����ǹ��� ������ �ȴ�..
	//Ư�� ActionListener �������̽��� actionPerformed()��� �߻�޼��带 ������ �����Ƿ� �����ڰ� �ݵ�� �ϼ����������
	public void actionPerformed(ActionEvent e) {
		System.out.println("��ư����");
	}
	public void keyReleased(KeyEvent e){ //Ű�� ������ ���� 
		System.out.println("Ű ����");
	}
	public void keyPressed(KeyEvent e){ //Ű�� ���궧 �����ϴ� �޼���
		System.out.println("Ű ������");
	}
	public void keyTyped(KeyEvent e){ // Ű�� �Է��Ҷ�
		System.out.println("Ű �Է�");
	}

		
}
