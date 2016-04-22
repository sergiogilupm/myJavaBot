/**
 * Java bot for IRC chat. Bot module
 * 
 * @author Sergio Gil
 * @version 1.0
 * 
 */

package javaBot;
import java.util.Hashtable;

import org.jibble.pircbot.*;

public class MyBot extends PircBot {

	private String superUser = "guil";
	private Hashtable<String, Team> teams;
	
    public MyBot() {
    	
        this.setName("MyJavaBot2");
        teams = new Hashtable<String, Team>();
    }
    
    
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
		
    	if (message.equalsIgnoreCase("time")) {
			 String time = new java.util.Date().toString();
			 sendMessage(channel, sender + ": The time is now " + time);
		}
		
		
		if (message.equalsIgnoreCase("show teams")) {
			 showTeams(channel);
		}
		
		if (message.equalsIgnoreCase("show my teams")) {
			 showMyTeams(sender, channel);
		}
		
		if (message.equalsIgnoreCase("reset")) {
			 reset(sender, channel);
		}
		
		
		
		if (message.startsWith("create team ")) {
			 String[] splited = message.split("\\s+");  
			 createTeam(splited[2], sender, channel);
		}
		
		if (message.startsWith("join ")) {
			 String[] splited = message.split("\\s+"); 
			 joinTeam(splited[1],sender,channel);
		}
		
		if (message.startsWith("show team ")) {
			 String[] splited = message.split("\\s+"); 
			 showTeam(splited[2],channel);
		}
		
		
		if (message.startsWith("leave team ")) {
			 String[] splited = message.split("\\s+"); 
			 leaveTeam(splited[2],sender,channel);
		}
		
		if (message.startsWith("delete team ")) {
			 String[] splited = message.split("\\s+"); 
			 deleteTeam(splited[2],sender,channel);
		}

	      
	}
     
    public void leaveTeam(String teamName, String nickname, String channel)
    {
    	if(!teams.containsKey(teamName))
    	{
    		sendMessage(channel, "User " + nickname + "has not joined team " + teamName + ". Cannot leave.");
    	}
    	else
    	{
    		Team team = teams.get(teamName);
    		if (team.removeMember(nickname) >= 0)
    		{
    			sendMessage(channel, "User " + nickname + " left team " + teamName);
    		}
    		else
    		{
    			sendMessage(channel, "User " + nickname + " is not part of team " + teamName);
    		}
    	}
    }
    
    public void joinTeam(String teamName, String nickname, String channel)
    {
    	if(!teams.containsKey(teamName))
    	{
    		sendMessage(channel, "Team " + teamName + " does not exist");
    	}
    	else
    	{
    		Team team = teams.get(teamName);
    		if (team.insertMember(nickname) >= 0)
    		{
    			sendMessage(channel, "User " + nickname + " joined team " + teamName);
    		}
    		else
    		{
    			sendMessage(channel, "User " + nickname + " is already in team " + teamName);
    		}
    	}
    }
    
    public void deleteTeam(String teamName, String nickname, String channel)
    {
    	if (!teams.containsKey(teamName))
    	{
    		sendMessage(channel, "Team " + teamName + " not found. Cannot remove");
    	}
    	else
    	{
    		teams.remove(teamName); 
    		sendMessage(channel, teamName + " was deleted");
    	}
    }
    
    public void createTeam(String teamName, String nickname, String channel)
    {
    	if (teams.containsKey(teamName))
    	{
    		sendMessage(channel, "Team " + teamName + " already exists");
    	}
    	else
    	{
    		Team team = new Team(nickname, teamName);
    		teams.put(teamName, team);
    		sendMessage(channel, "Team " + teamName + " created");
    	}
    }
    
    public void showTeam(String teamName, String channel)
    {
    	if (!teams.containsKey(teamName))
    	{
    		sendMessage(channel, "Team " + teamName + " does not exist");
    	}
    	else
    	{
    		Team team = teams.get(teamName);
    		String[] teamMembers = team.getMembers();
    		String message;
    		
    		message = "Team members of " + teamName + " are: ";
    		
    		for (String name : teamMembers)
    		{
    			message = message.concat(name + " ");
    		}
    		
    		sendMessage(channel, message);
    	}
    }
    
    
    public void showTeams(String channel)
    {
    	String message;
    	
    	if (teams.size() == 0)
    	{
    		message = "<No teams to show>";
    	}
    	else
    	{
        	message = "The list of teams are: ";
        	for (String teamName : teams.keySet())
        	{
        		message = message.concat(teamName + " ");
        	}
    	}

    	sendMessage(channel, message);
    }
    
    public void showMyTeams(String nickname, String channel)
    {
    	int teamCount = 0;
    	String message = nickname + " is in the following teams: ";

    	
    	for (String teamName : teams.keySet())
    	{
    		if (isInTeam(nickname, teams.get(teamName)))
    		{
    			teamCount++;
    			message = message.concat(teamName + " ");
    		}
    	}
    	
    	if (teamCount == 0)
    	{
    		message = nickname + " does not belong to any team";
    	}
    	
    	System.out.println("message: " + message);
    	sendMessage(channel, message);
    }
    
    private boolean isInTeam(String nickname, Team team)
    {
    	if (team.isMember(nickname))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    
    public void reset (String nickname, String channel)
    {
    	if (nickname.equals(this.superUser))
    	{
    		teams.clear();
    		sendMessage(channel, "Contents erased");
    	}
    	else
    	{
    		sendMessage(channel, "Not enough privileges to reset");
    	}
    }
    
    
    
}
