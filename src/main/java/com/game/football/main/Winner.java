package com.game.football.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.game.football.team.Team;
import com.game.football.thread.MyThread;
import com.game.football.tournament.Tournament;

public class Winner {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		int teams = 0;
		int winListSize = 0;
		Tournament tournament = new Tournament();
		MyThread myThread = new MyThread();
		while (true) {
			System.out.println("1.Register \n2.Start the Game \n3.Exit");
			System.out.println("Enter your choice");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

			int choice = 0;
			try {
				choice = Integer.parseInt(bufferedReader.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			switch (choice) {
			case 1:

				ArrayList<Team> arrayList = new ArrayList<Team>();

				System.out.println("Enter the number of teams");

				try {
					teams = Integer.parseInt(bufferedReader.readLine());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (teams > 1) {
					for (int i = 0; i < teams; i++) {
						Team team = new Team();
						team.setTeamName("Team" + i);
						arrayList.add(team);

					}
					tournament.setParticipantList(arrayList);

				} else
					System.out.println("Number of teams participating should be atleast 2");
				break;
			case 2:
				long start = System.currentTimeMillis();
				int checkVal = teams;
				if (checkVal % 2 == 0) {
					winListSize = (myThread.getWinList().size());

					while (winListSize != 1) {
						if (winListSize % 2 == 0) {
							winListSize = tournament.startTheTournamentEvenTeams(teams);
							tournament.getParticipantList().clear();
							tournament.getParticipantList().addAll(myThread.getWinList());
							myThread.getWinList().removeAll(myThread.getWinList());
						} else {

							winListSize = tournament.startTheTournamentOddTeams(teams);
							tournament.getParticipantList().clear();
							tournament.getParticipantList().addAll(myThread.getWinList());
							myThread.getWinList().removeAll(myThread.getWinList());

						}
					}
				}

				else {
					while (winListSize != 1) {
						winListSize = tournament.startTheTournamentOddTeams(teams);
						tournament.getParticipantList().clear();
						tournament.getParticipantList().addAll(myThread.getWinList());
						myThread.getWinList().removeAll(myThread.getWinList());
					}
				}
				
				System.out.println("Final Execution time : " +(System.currentTimeMillis()-start));
				break;

			case 3:
				Runtime.getRuntime().halt(0);
				break;
			default:
				System.out.println("Wrong Choice");

			}
		}

	}

}
