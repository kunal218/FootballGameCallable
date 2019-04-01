package com.game.football.tournament;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.*;
import org.junit.Test;
import org.mockito.Mock;
import com.game.football.*;

import com.game.football.team.Team;

public class TestTournament {

		
		private Tournament tournament = new Tournament();
		
	
	
	@Test
	public void testStartTheTournamentEvenTeams() throws InterruptedException, ExecutionException {
		ArrayList<Team > arrayList = new ArrayList<Team>();
		for(int i=0;i<4;i++) {
		Team  t = new Team();
		t.setTeamName("Team"+i);
		arrayList.add(t);
		}
		
		tournament.setParticipantList(arrayList);
		
		int teams = tournament.startTheTournamentEvenTeams(4);
		assertEquals(2, teams);
	
	}
	@Test
	public void testStartTheTournamentEvenTeamsWithDraw() throws InterruptedException, ExecutionException {
		ArrayList<Team > arrayList = new ArrayList<Team>();
		for(int i=0;i<4;i++) {
		Team  t = new Team();
		t.setTeamName("Team"+i);
		arrayList.add(t);
		}
		
		tournament.setParticipantList(arrayList);
		
		int teams = tournament.startTheTournamentEvenTeams(4);
		
		
		assertEquals(3, teams);
		
	}
	@Test
	public void testStartTheTournamentOddTeams() throws InterruptedException, ExecutionException, TimeoutException {
		ArrayList<Team > arrayList = new ArrayList<Team>();
		for(int i=0;i<5;i++) {
		Team  t = new Team();
		t.setTeamName("Team"+i);
		arrayList.add(t);
		}
		
		tournament.setParticipantList(arrayList);
		
		int teams = tournament.startTheTournamentOddTeams(5);
		assertEquals(3, teams);
	
	}
	@Test
	public void testStartTheTournamentOddTeamsWithDraw() throws InterruptedException, ExecutionException, TimeoutException {
		ArrayList<Team > arrayList = new ArrayList<Team>();
		for(int i=0;i<5;i++) {
		Team  t = new Team();
		t.setTeamName("Team"+i);
		arrayList.add(t);
		}
		
		tournament.setParticipantList(arrayList);
		
		int teams = tournament.startTheTournamentOddTeams(5);
		assertEquals(4, teams);
	
	}
	

}
