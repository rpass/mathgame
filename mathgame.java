import java.util.Scanner;
import java.util.InputMismatchException;
public class mathgame
{
	//TODO comment
	public static void main(String[] args) 
	{
		UI game = new UI();
	}
}
class UI
{
	public UI(){
		//Setting up input scanner and begin the ui with a message prompt
		Scanner p = new Scanner(System.in);
		Question q;
		String inp;
		int score = 0;
		int negscore = 0;
		int mode = 1;
		int userdifficulty = 0;

/*		int a = 3;
		int b = 2;
		double ans = (double)a/b;
		System.out.println("ans = " + ans);
		int bns = (int)ans;
		System.out.println("bns = " + bns);
		double cns = Math.floor(ans);
		int dns = (int)cns;
		System.out.println("cns = " + cns + "\ndns = " + dns);*/

		System.out.println("Choose your mode:");
		System.out.println("1. Addition");
		System.out.println("2. Subtraction");
		System.out.println("3. Division");
		System.out.println("4. Multiplication");
		System.out.println("0. Random");

		mode = p.nextInt();

		System.out.println("Choose your difficulty");
		System.out.println("1. Single-digit ops");
		System.out.println("2. Double-digit ops");
		System.out.println("3. etc.");
		System.out.println("4. etc.");
		System.out.println("0. Random length ops");

		userdifficulty = p.nextInt();

		System.out.println("Type 'x' to quit");
		outer:
			while(true){
				/*switch(mode){
					case 1:
						q = new Question(userdifficulty, '+');
						break;
					case 2:
						q = new SubtractionQ(userdifficulty);
						break;
					case 3:
						q = new RatioQ(userdifficulty);
						break;
					case 4:
						System.out.println("has not been implemented yet.");
					case 0:
						System.out.println("has not been implemented yet.");
					default:
						System.out.println("goodbye.");
						break outer;
				}*/

				q = new RatioQ(userdifficulty);
				q.askQuestion();
/*				System.out.println(q.genAnswerString());
*/					while(true){
						inp = p.next();
						if(inp.charAt(0) == 'x'){
							break outer;
						}
						if(q.test("" + inp) > 0){
							score += 1;
							break;
						}
						System.out.println("..nope..");
						negscore++;
						q.askQuestion();
					}
			}

		int attempts = score + negscore;
		if(attempts == 0)
			System.out.println("Goodbye.");
		else{
			System.out.println("Well done, your score was: " + score + "!");
			System.out.println("you got " + score + "/" + (attempts) + " correct!");
			System.out.println("That is " + (score*100)/(attempts) + "%");			
		}
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
	private int difficulty; //1 = single-digit operands, 2 = two-digit, etc.
	private char operator;

	/*--------------------------*/

	/*Constuctor*/
	public Question(int difficulty, char operator){
		if(difficulty == 0){
			generateDifficulty();
		}else{
			setDifficulty(difficulty);
		}

		setOperator(operator);
		generateQuestionOperands();
		setQuestionString(getVal1(), getVal2());
		setAnswerString();
	}

	/*Methods*/
	public void askQuestion(){
		System.out.print(getQuestion());
	}

	public int test(String answer){
		if(!answerString.equals(answer)){
			return 0;
		}
		return 1;
	}
	public void generateDifficulty(){
		int difficulty = 1 + (int)(Math.random() * 4);
		setDifficulty(difficulty);
	}
	public void generateQuestionOperands(){
		int range = (int)Math.pow(10, getDifficulty()) - 1;
		int val1 = 1 + (int)(Math.random() * range);
		int val2 = 1 + (int)(Math.random() * range);
		setVals(val1, val2);
	}

	/*Getters & Setters*/

	public void setQuestionString(int val1, int val2){
		/*length of each value - needed for formatting*/
		int len1 = String.valueOf(val1).length();
		int len2 = String.valueOf(val2).length();
		char operator = getOperator();

		/**/
		String padding1 = "     ";
		String padding2 = "     ";

		/*the length of the longer value*/
		int maxlength = Math.max(len1, len2);
		/*difference in length between values*/
		int diflength = len1 - len2;
		/*Create a padding string of x spaces (" ") where x is the difference in length between the input values*/
		String padding = new String(new char[Math.abs(diflength)]).replace("\0", " ");
		/*Compare lengths of values to decide where to put the padding*/
		if(diflength < 0){
			padding1 += padding;
		}else if(diflength > 0){
			padding2 += padding;
		}
		/*Set the question String with correct padding*/
		this.questionString = padding1 + val1 + "\n" + padding2 + val2 + " " + operator + "\n=";
	}
	public void setOperator(char operator){
		this.operator = operator;
	}
	public String genAnswerString(){ /*MAybe this should be abstract*/
		int ans = this.val1 + this.val2;
		String a = "" + ans;
		return a;
	}
	public void setAnswerString(){ 
		this.answerString = genAnswerString();
	}

	public void setVals(int val1, int val2){
		this.val1 = val1;
		this.val2 = val2;
	}

	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}
	public int getDifficulty(){
		return difficulty;
	}
	public int getVal1(){
		return val1;
	}
	public int getVal2(){
		return val2;
	}
	public String getQuestion(){
		return questionString;
	}
	public String getAnswer(){
		return answerString;
	}

	public char getOperator(){
		return operator;
	}
}


/*Child classes for special question types*/
class SubtractionQ extends Question{

	public SubtractionQ(int difficulty){
		super(difficulty, '-');
	}

	public String genAnswerString(){
		int ans = getVal1() - getVal2();
		String a = "" + ans;
		return a;
	}
}

class RatioQ extends Question{
	public RatioQ(int difficulty){
		super(difficulty, '/');
	}

	public String genAnswerString(){
		double ans = (double)getVal1() / getVal2();
		ans = (Math.floor(ans * 100))/100;
		String a = "" + ans;
		return a;
	}
}

/*
	private enum category {
		ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
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
	}*/