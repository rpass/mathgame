import java.util.Scanner;
public class mathgame
{
	public static void main(String[] args)
	{
		Scanner p = new Scanner(System.in);
		System.out.println("Type 'x' to quit");
		ask();
		while(p.next().charAt(0) != 'x'){
			ask();	
		}
		p.close();
	}

	public static void ask(){
		Scanner s = new Scanner(System.in);
		int range = 10;
		int val1 = (int)(Math.random() * range);
		int val2 = (int)(Math.random() * range);
		System.out.print(val1 + " + " + val2 + " = ");

		int inp = (int)s.nextInt();

		if(val1 + val2 == inp){
			System.out.println("Congrats!");
		} else {
			System.out.println("Boo!");
		}
		s.close();
	}
}