package craft;
public class Car{	
	static int price= 100;
	String company="kia";
	String name="G90";
	String color="black";


	public void run(){
		System.out.println("Â÷°¡°©´Ï´Ù");
	}

	public static void main(String []args){
		Car c1 = new Car();
		int price =50;
		Car c2 = new Car();

		c1.price=200;
		price=500;
		System.out.println(c2);
	}


}
