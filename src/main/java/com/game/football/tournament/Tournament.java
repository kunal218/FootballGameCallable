package com.game.football.tournament;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.game.football.file.FileOperations;
import com.game.football.team.Team;
import com.game.football.thread.MyThread;

public class Tournament {

	private ArrayList<Team> participantList = new ArrayList<Team>();
	private ArrayList<Team> winnerList = new ArrayList<Team>();
	private static int roundNumber = 1;
	private MyThread myThread ;
	

	public ArrayList<Team> getParticipantList() {
		return participantList;
	}

	public void setParticipantList(ArrayList<Team> participantList) {
		this.participantList = participantList;
	}

	public ArrayList<Team> getWinnerList() {
		return winnerList;
	}

	public void setWinnerList(ArrayList<Team> winnerList) {
		this.winnerList = winnerList;
	}

	
	/**
	 * This methods schedules threads using executor service and returns the size of
	 * winList after one round
	 * 
	 * @param teams
	 * @return size of winlist
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public int startTheTournamentEvenTeams(int teams) throws InterruptedException, ExecutionException {
		int sizeOfPool = teams / 2;

		ExecutorService executorService = Executors.newFixedThreadPool(sizeOfPool);
		CompletionService<Team> completionService = new ExecutorCompletionService<Team>(executorService);
		setWinnerList(getParticipantList());
		FileOperations fileOperations = new FileOperations();
		myThread=new MyThread();
		long start = System.currentTimeMillis();
		fileOperations.addRoundNumberToFile(roundNumber++,myThread.getWinnerPath(),myThread.getLoserPath()
				,myThread.getDrawPath());
		for (int i = 0; i < (getWinnerList().size()); i += 2) {
			Team t1 = getWinnerList().get(i);
			Team t2 = getWinnerList().get(i + 1);
			
			
			completionService.submit(new MyThread(t1, t2));
			
			Future<Team >future = null;
			try {
				 future =completionService.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Future Output :"+future.get());
			
		}
	/*	
		for(int i=0;i<teams/2;i++) {
			}
	*/	executorService.shutdown();
		while (!executorService.isTerminated()) {
		}
		
		System.out.println("In Winner size: " + myThread.getWinList().size());
		System.out.println("In Winner " + myThread.getWinList().toString());
		System.out.println(System.currentTimeMillis() - start);

		return (myThread.getWinList().size());
	}

	/**
	 * This method also schedules threads but it is wriiten to handle odd number of
	 * teams in which the odd team plays with winners of other matches in that round
	 * 
	 * @param teams
	 * @return size of WinList
	 * @throws ExecutionException  
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	public int startTheTournamentOddTeams(int teams) throws InterruptedException, ExecutionException, TimeoutException {
		int sizeOfPool = teams / 2;

		ExecutorService executorService = Executors.newFixedThreadPool(sizeOfPool);
		CompletionService<Team> completionService = new ExecutorCompletionService<Team>(executorService);
		setWinnerList(getParticipantList());
		FileOperations fileOperations = new FileOperations();
		myThread=new MyThread();
		long start = System.currentTimeMillis();
		fileOperations.addRoundNumberToFile(roundNumber++,myThread.getWinnerPath(),myThread.getLoserPath()
				,myThread.getDrawPath());
		Team odd = getWinnerList().get((getWinnerList().size()) - 1);
		myThread.getWinList().add(odd);
		File file = new File("Winner.txt");
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileWriter fileWriter1 = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter1 = new FileWriter(file, true);

			bufferedWriter = new BufferedWriter(fileWriter1);
			synchronized (bufferedWriter) {

				bufferedWriter.write(odd.getTeamName() + " ");
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			bufferedWriter.close();
			fileWriter1.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < ((getWinnerList().size()) - 1); i += 2) {
			Team t1 = getWinnerList().get(i);
			Team t2 = getWinnerList().get(i + 1);
			executorService.submit(new MyThread(t1, t2));

		
			Future<Team >future = null;
	
			
			try {
				 future =completionService.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			System.out.println("Future Output :"+future.get(2000, TimeUnit.MILLISECONDS));
			
		}
		
		/*for(int i=0;i<teams/2;i++) {
		}*/
		executorService.shutdown();
		while (!executorService.isTerminated()) {
		}
		
		System.out.println(" Total Number of Winners: " + myThread.getWinList().size());
		System.out.println(" Winners " + myThread.getWinList().toString());
		System.out.println("\n Execution Time :"+(System.currentTimeMillis() - start));

		return (myThread.getWinList().size());
	}

}
