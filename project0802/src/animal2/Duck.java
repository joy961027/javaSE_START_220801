package animal2;

public class  Duck{
	String name="집오리"; //property,멤버변수
	int age=3;
	String color="white";

	public void quack(){
		System.out.println("꽥꽥");
	}
	public static void main(String[] args){
		//오리의 나이를 5세로 변경후 그 나이를 출력.
		int x = 7;
		Duck d = new Duck();
		d.age = 5;
		System.out.println(d.age);
	}
}
