/**
 * Java bot for IRC chat. Team module
 * 
 * @author Sergio Gil
 * @version 1.0
 * 
 */

package javaBot;

import java.util.Arrays;
import java.util.LinkedList;

public class Team {

	private LinkedList<String> members = new LinkedList<String>();
	private String op;
	
	
	public Team(String nickname, String teamName){
		setOp(nickname);
	}
	
	public int insertMember(String nickname){
		
		/*if(members.isEmpty())
		{
			members.addFirst(nickname);
			setOp(nickname);
		}
		else
		{*/
			if (members.contains(nickname))  // If member already in team we don't insert it again
			{
				return -1;
			}
			else
			{
				members.addLast(nickname);
			}
			
		//}
		
		return 0;
	}
	
	public int removeMember(String nickname)
	{
		if (members.contains(nickname))
		{
			members.remove(nickname);
		}
		else
		{
			return -1;
		}
		
		return 0;
	}
	

	public String[] getMembers()
	{
		Object[] objectMemberList = members.toArray(); 
		
		String[] memberList = Arrays.copyOf(objectMemberList, objectMemberList.length, String[].class);
		
		return memberList;
	}
	
	public boolean removeTeam (String nickname)
	{
		if (isOp(nickname))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean isMember(String nickname)
	{
		if (members.contains(nickname))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	private boolean isOp(String nickname)
	{
		if(nickname.equals(this.op))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private int setOp(String nickname)
	{
		op = nickname;
		return 0;
	}
}
