package src;

import src.player.Participant;

import java.util.List;
import java.util.Objects;

public class Team {
    private String teamName;
    private long teamID;
    private List<String> playingPlayers;
    private List<Participant> backupPlayers;
    private Participant coach;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public long getTeamID() {
        return teamID;
    }

    public void setTeamID(long teamID) {
        this.teamID = teamID;
    }

    public List<String> getPlayingPlayers() {
        return playingPlayers;
    }

    public void setPlayingPlayers(List<String> playingPlayers) {
        this.playingPlayers = playingPlayers;
    }

    public List<Participant> getBackupPlayers() {
        return backupPlayers;
    }

    public void setBackupPlayers(List<Participant> backupPlayers) {
        this.backupPlayers = backupPlayers;
    }

    public Participant getCoach() {
        return coach;
    }

    public void setCoach(Participant coach) {
        this.coach = coach;
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
