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
		int negscore = 0;
		System.out.println("Type 'x' to quit");
		
		/*Testing*/
/*		q = new Question();
		for (int i = 0; i < 20 ; i++) {	
			q.generateDifficulty();
		}*/

		outer:
			while(true){
				q = new Question();
				q.askQuestion();
					while(true){
						inp = p.next();
						if(inp.charAt(0) == 'x'){
							break outer;
						}
						if(q.test("" + Integer.parseInt(inp)) > 0){
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
			attempts = 1;
		System.out.println("Well done, your score was: " + score + "!");
		System.out.println("you got " + score + "/" + (attempts) + " correct!");
		System.out.println("That is " + (score*100)/(attempts) + "%");
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

	/*--------------------------*/

	/*Constuctor*/
	public Question(){
		generateDifficulty();
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
		int range = (int)Math.pow(10, getDifficulty());
		int val1 = (int)(Math.random() * range);
		int val2 = (int)(Math.random() * range);
		setVals(val1, val2);
	}

	/*Getters & Setters*/

	public void setQuestionString(int val1, int val2){
		/*length of each value - needed for formatting*/
		int len1 = String.valueOf(val1).length();
		int len2 = String.valueOf(val2).length();

		/**/
		String padding1 = "";
		String padding2 = "";

		/*the length of the longer value*/
		int maxlength = Math.max(len1, len2);
		/*difference in length between values*/
		int diflength = len1 - len2;
		/*Create a padding string of x spaces (" ") where x is the difference in length between the input values*/
		String padding = new String(new char[Math.abs(diflength)]).replace("\0", " ");
		/*Compare lengths of values to decide where to put the padding*/
		if(diflength < 0){
			padding1 = padding;
		}else if(diflength > 0){
			padding2 = padding;
		}
		/*Set the question String with correct padding*/
		this.questionString = padding1 + val1 + "\n" + padding2 + val2 + " +\n=";
	}

	public void setAnswerString(){
		int ans = this.val1 + this.val2;
		this.answerString = "" + ans;
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