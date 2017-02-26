package com.Silvia.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

public class RockPaperScissorsTest {

	RockPaperScissors testCode;
	private final ByteArrayOutputStream outString = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errorString = new ByteArrayOutputStream();

	@Rule
	public final EnvironmentVariables environmentVariables = new EnvironmentVariables();
	

	@Before
	public void logStreams() {
	    System.setOut(new PrintStream(outString));
	    System.setErr(new PrintStream(errorString));
	}

	@After
	public void resetStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	

	@Test
	public void setEnvironmentVariable() {
		environmentVariables.set("gameIterations", "-1");
		assertEquals("-1", System.getenv("filePath"));
	}

	@Test
	public void iterateGameTest(){
		testCode = new RockPaperScissors();
		testCode.initializeOutputFile();
		testCode.iterateGame("fair");
		assertEquals(0, testCode.drawCounter);
		assertEquals(0, testCode.player1WinCounter);
		assertEquals(0, testCode.player2WinCounter);
	}
	
	@Test
	public void rockPaperScissorsArrayTest(){
		String[] testOptions = {"rock", "paper", "scissors"};
		testCode = new RockPaperScissors();
		for (String value : testOptions){
			boolean exists = Arrays.asList(testCode.options).contains(value.toLowerCase());
			assertTrue(exists);
		}
	}

}
