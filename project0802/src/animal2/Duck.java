package animal2;

public class  Duck{
	String name="������"; //property,�������
	int age=3;
	String color="white";

	public void quack(){
		System.out.println("�в�");
	}
	public static void main(String[] args){
		//������ ���̸� 5���� ������ �� ���̸� ���.
		int x = 7;
		Duck d = new Duck();
		d.age = 5;
		System.out.println(d.age);
	}
}
