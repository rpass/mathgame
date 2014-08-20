import java.util.Scanner;
public class mathgame
{
	public static void main(String[] args)
	{
		Scanner p = new Scanner(System.in);
		System.out.println("Type 'x' to quit");
		Question q = new Question();
		q.askQuestion();
		String inp = p.next();
		while(inp.charAt(0) != 'x'){
			q.test("" + Integer.parseInt(inp)); //loop problem
			q = new Question();
			q.askQuestion();
			inp = p.next();
		}
		p.close();
	}

/*	public static void ask(){
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
	}*/
}

class Question
{
	/*---------------Class variables----------------*/
	private String questionString;
	private String answerString;
	private int val1;
	private int val2;
	/*private char op;*/ //not needed

	private enum category {
		ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
	}
	private category cat;
	/*--------------------------*/

	public Question(){
		generateQuestionOperands();
		cat = category.ADDITION;	 // temp, will be replace with cat generator
		setQuestionString(getVal1(), getVal2(), getOperator());
		setAnswerString();
	}

	public void test(String answer){
		if(answerString.equals(answer)){
			System.out.println("Congratulations!");
		}else{
			System.out.println("Booo!");
		}
	}
	public void askQuestion(){
		System.out.print(getQuestion());
	}


	public String getQuestion(){
		return questionString;
	}
	public String getAnswer(){
		return answerString;
	}
	public void setQuestionString(int val1, int val2, char op){
		this.questionString = "" + val1 + " " + op + " " + val2 + " = ";
	}
	public void setAnswerString(){
		category cat = this.getCategory();
		int ans = -99;
		switch (cat){
			case ADDITION : 		ans = val1 + val2;
									break;
			case SUBTRACTION : 		ans = val1 - val2;
									break;
			case MULTIPLICATION : 	ans = val1 * val2;
									break;
			case DIVISION : 		ans = val1 / val2;
									break;
		}
		this.answerString = "" + ans;
	}
	public category getCategory(){
		return cat;
	}
	public char getOperator(){
		category cat = this.getCategory();
		char op = '+';
		switch (cat){
			case ADDITION : op = '+';
							break;
			case SUBTRACTION : op = '-';
								break;
			case MULTIPLICATION : op = '*';
									break;
			case DIVISION : op = '/';
							break;
		}

		return op;
	}
	public void setVals(int val1, int val2){
		this.val1 = val1;
		this.val2 = val2;
	}
	public int getVal1(){
		return val1;
	}
	public int getVal2(){
		return val2;
	}
	//The useful functions:
	public void generateQuestionOperands(){
		/*this.range = range;*/
		int range = 10;
		int val1 = (int)(Math.random() * range);
		int val2 = (int)(Math.random() * range);
		setVals(val1, val2);
	}
	public void generateOperator(){

	}
}