import java.util.Scanner;
public class mathgame
{
	//TODO comment
	public static void main(String[] args) 
	{
		//Setting up input scanner and begin the ui with a message prompt
		Scanner p = new Scanner(System.in);
		Question q;
		String inp;
		int score = 0;
		System.out.println("Type 'x' to quit");

		while(true){
			q = new Question();
			q.askQuestion();

			inp = p.next();

			if(inp.charAt(0) == 'x'){
				break;
			}

			score += q.test("" + Integer.parseInt(inp));
		}

		System.out.println("Well done, your score was: " + score + "!");
		p.close();
	}
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
	private category qCategory;
	/*--------------------------*/

	public Question(){
		generateQuestionOperands();
		qCategory = category.ADDITION;	 // temp, will be replace with cat generator
		setQuestionString(getVal1(), getVal2(), getOperator());
		setAnswerString();
	}

	public int test(String answer){
		if(!answerString.equals(answer)){
			System.out.println(" ...Nope...");
			return 0;
		}
		return 1;
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
		category qCategory = this.getCategory();
		int ans = -99;
		switch (qCategory){
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
		return qCategory;
	}
	public char getOperator(){
		category qCategory = this.getCategory();
		char op = '+';
		switch (qCategory){
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