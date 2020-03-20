package com.example.cricket.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricket.service.CricketService;
import com.example.cricket.util.Team;

@RestController
@RequestMapping("/cricket")
@CrossOrigin(origins = "*")
public class CricController {

	@Autowired
	CricketService cs;
	
	@GetMapping("/allTeam")
	public ResponseEntity<String> getAllTeam(){
		return cs.getAllTeam();
		
	}
	
	@PostMapping("/newTeam")
	public ResponseEntity<String> addTeamInfo (@RequestBody HashMap<String,Object> t){
		
		return cs.addTeamInfo((int)t.get("team_id"),(String)t.get("team_name"),(int)t.get("team_matches_won"),(int)t.get("team_matches_lost")
				,(int)t.get("team_matches_draw"));
		
	}
	
	
	@DeleteMapping("/deleteTeam/{team_id}")
	
		public ResponseEntity<String> deleteTeamData (@PathVariable int team_id){
	
		return cs.deleteTeamData(team_id);
		
	}
	
	@PutMapping("/updateTeam/{team_id}")
	public ResponseEntity<String> updateTeamInfo(@PathVariable int team_id,@RequestBody HashMap<String,Object> upTeam ){
		return cs.updateTeamInfo(team_id,(String)upTeam.get("team_name"),(int)upTeam.get("team_matches_won"),(int)upTeam.get("team_matches_lost")
				,(int)upTeam.get("team_matches_draw"));
		
	}
	
	
	///FOR PLAYERS
	
	@PostMapping("/addPlayer")
	public ResponseEntity<String> addPlayer(@RequestBody HashMap<String,Object> p){
		
		return cs.addPlayer((String)p.get("player_name"),(int)p.get("player_age"),(int)p.get("team_team_id"));
	}
	
	
	@GetMapping("/allPlayers")
	
		public ResponseEntity<String> getPlayers(){
		return cs.getPlayers();
	}
	
}
