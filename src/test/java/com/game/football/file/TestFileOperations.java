package com.game.football.file;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.game.football.team.Team;

@RunWith(MockitoJUnitRunner.class)
public class TestFileOperations {

	
	FileOperations fileOperations = new FileOperations();
	private String actual = null;
	private String winnerActual=null;
	private String loserActual=null;
	public String getLine(String path) {
		File file = new File(path);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		try {
					
				 actual = bufferedReader.readLine();
			return actual;
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			bufferedReader.close();
			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

	}
	
	@Test
	public void testAddToDraw() {
	
		Team team1=new Team();
		team1.setTeamName("Manchester United");
		Team team2=new Team();
		team2.setTeamName("Chelsea");
	
		fileOperations.addToDraw(team1, team2, "DrawTest.txt");
		actual=getLine("DrawTest.txt");
		
		String expected="Manchester United" + " Draws with " + "Chelsea";
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void testAddToFile() {
		Team team1=new Team();
		team1.setTeamName("PSG");
		Team team2=new Team();
		team2.setTeamName("Real Madrid");
		
		fileOperations.addToFile(team1, team2, 1, "WinnerTest.txt", "LoserTest.txt");
		winnerActual=getLine("WinnerTest.txt");
		loserActual=getLine("LoserTest.txt");
		
		assertEquals("PSG ", winnerActual);
		assertEquals("Real Madrid ", loserActual);

		
	}
	@Test 
	public void testAddRoundNumberToFile() {
		
		int roundnumber = 1;
		
		fileOperations.addRoundNumberToFile(roundnumber,"RoundTest.txt" , "RoundTest.txt", "RoundTest.txt");
	
		
	}
	

}
