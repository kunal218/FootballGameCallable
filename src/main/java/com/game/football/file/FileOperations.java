package com.game.football.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.game.football.team.Team;

public class FileOperations {

	/**
	 * This method prints round number to winner.txt , loser.txt , and draw.txt
	 * 
	 * @param roundNumber
	 */
	public void addRoundNumberToFile(int roundNumber,String winnerPath,String loserPath,String drawPath) {
		File file1 = new File(loserPath);
		try {
			file1.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file1, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(" \n ******** Round  : \n" + roundNumber + " Started ***********");
			bufferedWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedWriter.close();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		File file2 = new File(winnerPath);
		try {
			file2.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileWriter fileWriter2 = null;
		BufferedWriter bufferedWriter2 = null;
		try {
			fileWriter2 = new FileWriter(file2, true);
			bufferedWriter2 = new BufferedWriter(fileWriter2);
			bufferedWriter2.write(" \n ******** Round  : \n" + roundNumber + " Started ***********");
			bufferedWriter2.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedWriter2.close();
			fileWriter2.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		File file3 = new File(drawPath);
		try {
			file3.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileWriter fileWriter3 = null;
		BufferedWriter bufferedWriter3 = null;
		try {
			fileWriter3 = new FileWriter(file3, true);
			bufferedWriter3 = new BufferedWriter(fileWriter3);
			bufferedWriter3.write(" \n ******** Round  : \n" + roundNumber + " Started ***********");
			bufferedWriter3.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedWriter3.close();
			fileWriter3.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method add both playing teams to draw.txt
	 * 
	 * @param team1
	 * @param team2
	 */
	public void addToDraw(Team team1, Team team2,String path) {
		File file = new File(path);
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

				bufferedWriter.write(team1.getTeamName() + " Draws with " + team2.getTeamName());
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

	}

	/**
	 * This method add winner to winner.txt and loser to loser.txt and if match is
	 * draw then add both wiiner and loser to winner.txt
	 * 
	 * @param winner
	 * @param loser
	 * @param check
	 */
	public void addToFile(Team winner, Team loser, int drawCheck,String winnerPath,String loserPath) {

		if (drawCheck == 1) {
			File file = new File(winnerPath);
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

					bufferedWriter.write(winner.getTeamName() + " ");
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

			File file1 = new File(loserPath);
			try {
				file1.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(file1, true);
				bufferedWriter = new BufferedWriter(fileWriter);
				synchronized (bufferedWriter) {

					bufferedWriter.write(loser.getTeamName() + " ");
					bufferedWriter.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bufferedWriter.close();
				fileWriter.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			File file = new File(winnerPath);
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

					bufferedWriter.write(winner.getTeamName() + " ");
					bufferedWriter.newLine();
					bufferedWriter.write(loser.getTeamName() + " ");
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

		}
	}

}
