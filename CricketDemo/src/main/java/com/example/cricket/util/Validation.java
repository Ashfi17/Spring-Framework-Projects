package com.example.cricket.util;

import java.util.HashMap;

public class Validation {
	
	public HashMap<String, String> validateTeam(String team_name,int matches_won,int matches_lost){
		HashMap<String,String> errors = new HashMap<String, String>();
		
		if(team_name.length() < 4)
			errors.put("team_name", "name should be grater than 2 char"); 
		
		 
		if(matches_won < 0)
			errors.put("team_matches_won", "matches won field cannot be less than 0");
		
		if(matches_lost < 0)
			errors.put("team_matches_lost", "matches lost field cannot be less than 0");
//		
//		String matches_lost = String.valueOf(team.getTeam_matches_lost());
//		
//		if( matches_lost == "")
//			errors.put("team_matches_lost", "matches lost field cannot be empty");
//		
//		String matches_draw = String.valueOf(team.getTeam_matches_draw());
//		
//		if( matches_draw == "")
//			errors.put("team_matches_draw", "matches draw field cannot be empty");	
		
		return errors;
	}

}
