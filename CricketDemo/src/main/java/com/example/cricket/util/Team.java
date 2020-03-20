package com.example.cricket.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	@Id
	private int team_id;
	private String team_name;
	private int team_matches_won;
	private int team_matches_lost;
	private int team_matches_draw;
	
	
	@OneToMany(mappedBy = "team")
	private List<Player> player = new ArrayList<>();
	
	public List<Player> getPlayer() {
		return player;
	}
	public void setPlayer(List<Player> player) {
		this.player = player;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public int getTeam_matches_won() {
		return team_matches_won;
	}
	public void setTeam_matches_won(int team_matches_won) {
		this.team_matches_won = team_matches_won;
	}
	public int getTeam_matches_lost() {
		return team_matches_lost;
	}
	public void setTeam_matches_lost(int team_matches_lost) {
		this.team_matches_lost = team_matches_lost;
	}
	public int getTeam_matches_draw() {
		return team_matches_draw;
	}
	public void setTeam_matches_draw(int team_matches_draw) {
		this.team_matches_draw = team_matches_draw;
	}
	
	
}
