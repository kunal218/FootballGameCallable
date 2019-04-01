package com.game.football.thread;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;

import com.game.football.file.FileOperations;
import com.game.football.team.Team;

public class MyThread implements Callable<Team> {

	private Team homeTeam;
	private Team awayTeam;
	private Random random = new Random();
	private boolean bool = false;
	private FileOperations fileOperations = new FileOperations();
	private static ArrayList<Team> winList = new ArrayList<Team>();
	private String drawPath = "Draw.txt";
	private String winnerPath = "Winner.txt";
	private String loserPath = "Loser.txt";

	
	

	public ArrayList<Team> getWinList() {
		return winList;
	}

	public void setWinList(ArrayList<Team> winList) {
		MyThread.winList = winList;
	}

	public String getDrawPath() {
		return drawPath;
	}

	public String getWinnerPath() {
		return winnerPath;
	}

	public String getLoserPath() {
		return loserPath;
	}


	public MyThread() {

	}

	public MyThread(Team homeTeam, Team awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}


	public Team call() throws Exception {
		ArrayList<Team> winner = execute(homeTeam, awayTeam);
		synchronized (this) {
			bool = true;
			try {
				for (Team t : winner) {
				   winList.add(t);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(
						"Exception Thread id " + Thread.currentThread().getId() + "  queue:" + winList.toString());
			}
		}
		return winner.get(0);
	}

	/**
	 * This method sets up matches and assigns scores (random ) to hometeam and
	 * awayteam and returns winning team
	 * 
	 * @param homeTeam
	 * @param awayTeam
	 * @return
	 */
	private ArrayList<Team> execute(Team homeTeam, Team awayTeam) {
		ArrayList<Team> arrayList = new ArrayList<Team>();
		homeTeam.setScore(random.nextInt(2));
		awayTeam.setScore(random.nextInt(3)+3);

		if (homeTeam.getScore() == awayTeam.getScore()) {
			System.out.println("\n" + homeTeam.toString() + " Draw " + awayTeam.toString());
			arrayList.add(homeTeam);
			arrayList.add(awayTeam);
			
			fileOperations.addToDraw(homeTeam, awayTeam,drawPath);
			fileOperations.addToFile(homeTeam, awayTeam, 0,winnerPath,loserPath);
			return arrayList;

		}

		if (homeTeam.getScore() > awayTeam.getScore()) {
			arrayList.add(homeTeam);
			fileOperations.addToFile(homeTeam, awayTeam,1,winnerPath,loserPath);
			return arrayList;

		} else if (homeTeam.getScore() < awayTeam.getScore())
			arrayList.add(awayTeam);
		fileOperations.addToFile(awayTeam, homeTeam,1,winnerPath,loserPath);
		return arrayList;

	}

	

}
