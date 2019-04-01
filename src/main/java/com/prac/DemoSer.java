package com.prac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import com.game.football.team.Team;

public class DemoSer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7039754753951895622L;

	public void add() {
		Team team1=new Team();
		team1.setTeamName("Team1");
		Team team2=new Team();
		team2.setTeamName("Chelsea");
		
		
		FileOutputStream file;
		try {
			file = new FileOutputStream("filef.ser");
			  ObjectOutputStream out = new ObjectOutputStream(file); 
			  out.writeObject(team1); 
			  out.close(); 
		        file.close(); 
		       
		          
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
          
        // Method for serialization of object 
       
        System.out.println("Object has been serialized");
		
		
		
		
		
		
		
	}
	public static void main(String[] args) {
		new DemoSer().add();
	}
}
