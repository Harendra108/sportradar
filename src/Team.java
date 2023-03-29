package src;

import src.player.Participant;

import java.util.List;
import java.util.Objects;

public class Team {
    private String teamName;
    private int teamID;
    private List<String> playingPlayers;
    private List<Participant> backupPlayers;
    private Participant coach;

    public Team(String teamName, int teamID) {
        this.teamName = teamName;
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public long getTeamID() {
        return teamID;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return teamID == team.teamID && teamName.equals(team.teamName) && playingPlayers.equals(team.playingPlayers) && backupPlayers.equals(team.backupPlayers) && coach.equals(team.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, teamID, playingPlayers, backupPlayers, coach);
    }
}
