package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Game {
    //Generate the ID
    private final int gameID;
    private final Team homeTeam;
    private final Team awayTeam;
    private GameStatus status;
    private int homeTeamScore;
    private int awayTeamScore;
    private Team winnerTeam;
    private int totalScore;


    public int getGameID() {
        return gameID;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Game(int gameID, Team homeTeam, Team awayTeam, GameStatus status,
                int homeTeamScore, int awayTeamScore) {
        this.gameID = gameID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.status = status;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public void setScore(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        totalScore = homeTeamScore + awayTeamScore;
    }

    public void finishGame() {
        if(homeTeamScore > awayTeamScore) {
            winnerTeam = homeTeam;
        } else if(awayTeamScore > homeTeamScore) {
            winnerTeam = awayTeam;
        } else {
            this.status = GameStatus.DRAWN;
            winnerTeam = null;
        }
    }

    public Map<String,Integer> getScore() {
        Map<String, Integer> teamScoreMap = new HashMap<>();
        teamScoreMap.put(homeTeam.getTeamName(), homeTeamScore);
        teamScoreMap.put(awayTeam.getTeamName(), awayTeamScore);
        return teamScoreMap;
    }

    public Optional<Team> getWinner() {
        return Optional.of(winnerTeam);
    }

    public int getTotalScoreForTheGame() {
        return totalScore;
    }

    @Override
    public String toString() {
        return homeTeam.getTeamName() + "-" + homeTeamScore + "; " + awayTeam.getTeamName() + "-" + awayTeamScore;
    }
}
