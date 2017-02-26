package com.Silvia.game;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

	//Variables definition
	public String[] options = {"rock", "paper", "scissors"};
	int player1RandomSelection;
	int player2RandomSelection;
	int player2UnfairSelection = 0; //rock
	int gameIterations = 10;
	String logString;
	String filePath = "D:/RockPaperScissors_Output.txt";
	PrintWriter outputFile;
	//Counters
	int player1WinCounter = 0;
	int player2WinCounter = 0;
	int drawCounter = 0;

	//Method definition
	public void makeAFairMove(){
		player1RandomSelection = new Random().nextInt(options.length);
		player2RandomSelection = new Random().nextInt(options.length);
		printToConsoleAndFile("Player 1 selected " + options[player1RandomSelection]);
		printToConsoleAndFile("Player 2 selected " + options[player2RandomSelection]);
	}

	public void makeAnUnfairMove(){
		player1RandomSelection = new Random().nextInt(options.length);
		player2RandomSelection = player2UnfairSelection;
		printToConsoleAndFile("Player 1 selected " + options[player1RandomSelection]);
		printToConsoleAndFile("Player 2 selected " + options[player2RandomSelection]);
	}

	public void computeWinner(){
		System.out.println("Results:");
		int substractSelections = player1RandomSelection - player2RandomSelection;
		if (substractSelections==0){
			printToConsoleAndFile("It's a draw!");
			drawCounter++;
		} else if (substractSelections==-1 || substractSelections==2){
			printToConsoleAndFile("Player 2 wins!");
			player2WinCounter++;
		} else if (substractSelections==1 || substractSelections==-2){
			printToConsoleAndFile("Player 1 wins!");
			player1WinCounter++;
		}
	}

	public void iterateGame(String fairnessSelection){
		if (!fairnessSelection.equalsIgnoreCase("fair") && !fairnessSelection.equalsIgnoreCase("unfair")){
			printToConsoleAndFile("Option does not exist, please try again.");
			return;
		}
		
		printToConsoleAndFile("Mode of play: " + fairnessSelection.toLowerCase());
		printToConsoleAndFile("---------------------------------------------------");
		printToConsoleAndFile("--------------------GAME START!--------------------");
		printToConsoleAndFile("---------------------------------------------------");
		
		for (int i=1; i<=gameIterations; i++){
			if(fairnessSelection.equalsIgnoreCase("fair")){
				printToConsoleAndFile("Game " + i);
				makeAFairMove();
			} else if (fairnessSelection.equalsIgnoreCase("unfair")){
				printToConsoleAndFile("Game " + i);
				makeAnUnfairMove();
			}
			computeWinner();			
			printToConsoleAndFile("-------------------------------");
		}
		printStatistics();
	}

	public void printStatistics(){
		printToConsoleAndFile("");
		printToConsoleAndFile("===========GAME STATS==========");
		printToConsoleAndFile("Player 1 won a total of " + player1WinCounter + " times.");
		printToConsoleAndFile("Player 2 won a total of " + player2WinCounter + " times.");
		printToConsoleAndFile("Out of all the games, " + drawCounter + " were a draw.");
		printToConsoleAndFile("");
	}

	public void initializeOutputFile(){
		try{
			outputFile = new PrintWriter(new FileWriter(filePath, true));
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void printToConsoleAndFile(String logString){
		System.out.println(logString);
		outputFile.println(logString);
	}

	//Main method
	public static void main(String[] args) {	
		System.out.println("Select mode of play: [fair]/[unfair]");
		Scanner inputReader = new Scanner(System.in);
		String modeOfPlay = inputReader.nextLine();
		inputReader.close();
		
		RockPaperScissors myGame = new RockPaperScissors();
		myGame.initializeOutputFile();
		myGame.iterateGame(modeOfPlay);	
		myGame.outputFile.close();
	}
}
