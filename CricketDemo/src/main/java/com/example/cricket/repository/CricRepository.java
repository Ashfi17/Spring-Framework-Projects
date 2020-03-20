package com.example.cricket.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import javax.persistence.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.cricket.util.Player;
import com.example.cricket.util.ResponseCreator;
import com.example.cricket.util.Team;


@Transactional
@Repository
public class CricRepository {
	
	@PersistenceContext
	private EntityManager entitymanager;
	
	Team team = new Team();
	Player player = new Player();
	
	
	public ResponseEntity<String> getAllTeam() {
		// TODO Auto-generated method stub
		try {
			Query query = entitymanager.createNativeQuery("Select * from team");
			//to use key value pair
			((org.hibernate.query.Query<Object>)query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			List<Object> s = ((org.hibernate.query.Query<Object>)query).list();
			
			return ResponseEntity.ok(ResponseCreator.response("data retrieved successfully", s, 200,null));
		}catch(Exception e) {
			
			System.out.println("Exception occures");
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot retrieve data",null,500,null));
			
		}
		
		
	}

	public ResponseEntity<String> addTeamInfo(int team_id, String team_name, int team_matches_won, int team_matches_lost,
			int team_matches_draw) {
		// TODO Auto-generated method stub
		
		try {
		team.setTeam_id(team_id);
		team.setTeam_name(team_name);
		team.setTeam_matches_won(team_matches_won);
		team.setTeam_matches_lost(team_matches_lost);
		team.setTeam_matches_draw(team_matches_draw);
		
		entitymanager.persist(team);
		
		entitymanager.flush();
		
		return ResponseEntity.ok(ResponseCreator.response("Added team data successfully", null, 200,null));
		}catch(Exception e) {
			System.out.println("Exception occures");
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot add team data",null,500,null));
		}
	}

	public ResponseEntity<String> deleteTeamData(int team_id) {
		// TODO Auto-generated method stub
		try {
			Team delteam = entitymanager.find(Team.class, team_id);
			
			if(delteam == null) {
				return ResponseEntity.ok(ResponseCreator.response("could not find object", null, 400,null));
			}
			
			else {
			
			entitymanager.remove(delteam);
			
			return ResponseEntity.ok(ResponseCreator.response("deleted team data successfully", null, 200,null));
			}
		}catch(Exception e) {
			System.out.println("Exception occures");
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot delete data",null,500,null));
		}
	}

	public ResponseEntity<String> updateTeamInfo(int team_id, String team_name, int team_matches_won,
			int team_matches_lost,int team_matches_draw) {
		// TODO Auto-generated method stub
		try {
			team.setTeam_id(team_id);
			team.setTeam_name(team_name);
			team.setTeam_matches_draw(team_matches_draw);
			team.setTeam_matches_won(team_matches_won);
			team.setTeam_matches_lost(team_matches_lost);
			
			
			entitymanager.merge(team);
	
			
			return ResponseEntity.ok(ResponseCreator.response("updated team data successfully", null, 200,null));
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot update team data",null,500,null));
			
		}
	}
	
	
	///////////////// PLAYERS //////////////////

	public ResponseEntity<String> addPlayer(String player_name, int player_age, int team_id) {
		// TODO Auto-generated method stub
		
		try {
			
			
			Team team1 = entitymanager.find(Team.class, team_id);
			
			if(team1 == null) {
				return ResponseEntity.ok(ResponseCreator.response("could not find object", null, 400,null));
			}
			
			else {
			
		player.setPlayer_age(player_age);
		player.setPlayer_name(player_name);
		player.setTeam(team1);
		
		entitymanager.persist(player);
		entitymanager.flush();
		return ResponseEntity.ok(ResponseCreator.response("created new player data successfully", null, 200,null));
			}
		
		}catch(Exception e) {
			System.out.println("Exception occured while creating player");
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot add player data",null,500,null)); 
		}
	}

	public ResponseEntity<String> getPlayers() {
		// TODO Auto-generated method stub
		
		
		try {
			Query query = entitymanager.createNativeQuery("Select * from player");
			//to use key value pair
			((org.hibernate.query.Query<Object>)query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			List<Object> s = ((org.hibernate.query.Query<Object>)query).list();
			
			return ResponseEntity.ok(ResponseCreator.response("all players retrieved successfully", s, 200,null));
		}catch(Exception e) {
			
			System.out.println("Exception occures");
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot retrieve players data",null,500,null));
			
		}
	}

}
