package com.example.cricket.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cricket.repository.CricRepository;
import com.example.cricket.util.ResponseCreator;
import com.example.cricket.util.Team;
import com.example.cricket.util.Validation;

@Service
public class CricketService {
	
	Validation vald = new Validation(); 
	Team team = new Team();
	
	@Autowired
	CricRepository cricRepo;

	public ResponseEntity<String> getAllTeam() {
		// TODO Auto-generated method stub
		
		return cricRepo.getAllTeam();
	}

	public ResponseEntity<String> addTeamInfo(int team_id, String team_name, int team_matches_won, int team_matches_lost, int team_matches_draw) {
		// TODO Auto-generated method stub
		
		
		
		HashMap<String, String> v = vald.validateTeam(team_name,team_matches_won,team_matches_lost);
		
		if(v.isEmpty())		
		return cricRepo.addTeamInfo(team_id,team_name,team_matches_won,team_matches_lost,team_matches_draw);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Validation Error 500! ", null, 500, v));
	}

	public ResponseEntity<String> deleteTeamData(int team_id) {
		// TODO Auto-generated method stub
		return cricRepo.deleteTeamData(team_id);
	}

	public ResponseEntity<String> updateTeamInfo(int team_id,  String team_name, int team_matches_won, int team_matches_lost, int team_matches_draw) {
		// TODO Auto-generated method stub
		return cricRepo.updateTeamInfo(team_id,team_name,team_matches_won,team_matches_lost,team_matches_draw);
	}

	public ResponseEntity<String> addPlayer(String name, int age, int team_id) {
		// TODO Auto-generated method stub
		return cricRepo.addPlayer(name,age,team_id);
	}

	public ResponseEntity<String> getPlayers() {
		// TODO Auto-generated method stub
		return cricRepo.getPlayers();
	}

	

}
