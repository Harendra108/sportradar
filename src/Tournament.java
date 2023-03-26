package src;

import java.util.*;
import java.util.stream.Collectors;

public class Tournament {
    private long tournamentID;
    private final List<Game> gamesList = new ArrayList<>(1);
    private final String tournamentLocation = "Wroclaw";//For this test, I am marking it as default one
    private Team winnerTeam;
    private Team runnerUpTeam;
    private Team thirdPlaceTeam;//above three entries are there in case we want to know who is the winner, runnerUp and the bronze team
    private final Map<Integer, Map<String, Integer>> gameIdScoreBoardMap = new HashMap<>(1);


    private static volatile boolean isTournamentStarted = false;

    public synchronized void startGame(Team homeTeam, Team awayTeam) {
        //gameID will be the autoIncrement of the current GameID.

        Optional<Game> max = gamesList.stream().max(Comparator.comparing(Game::getGameID));
        int gameID = max.map(Game::getGameID).orElse(0) + 1;
        Game game = new Game(gameID, homeTeam, awayTeam, GameStatus.STARTED, 0, 0);
        gamesList.add(game);
        if(!isTournamentStarted) {
            isTournamentStarted = true;
            updateScoreBoard();
        }
    }

    public void finishTournament() {
        isTournamentStarted = false;
    }

    public synchronized void updateScore(int gameID, int homeTeamScore, int awayTeamScore) {
        Optional<Game> game = gamesList.stream().filter(g -> g.getGameID() == gameID).findFirst();
        if(game.isPresent()) {
            Game requiredGame = game.get();
            requiredGame.setStatus(GameStatus.IN_PROGRESS);
            requiredGame.setScore(homeTeamScore, awayTeamScore);
        }
    }

    public void updateScoreBoard() {
        while(isTournamentStarted) { // while the tournament is in progress, keep the scoreboard updated.
            Runnable r = this::updateScoreBoardOfAllGames;
            Thread t = new Thread(r);
            t.start();
            if(!isTournamentStarted) {
                break;
            }
        }
    }

    private void updateScoreBoardOfAllGames() {
        gamesList.forEach(game -> gameIdScoreBoardMap.put(game.getGameID(), game.getScore()));
    }

    public void finishGame(int gameID) {
        Optional<Game> gameToRemove = gamesList.stream().filter(game -> game.getGameID() == gameID).findFirst();
        if(gameToRemove.isPresent()) {
            Game game = gameToRemove.get();
            game.setStatus(GameStatus.FINISHED);
            game.finishGame();
            gamesList.remove(game);
        }
    }

    public List<String> getSummary() {
        /*
          1. Sort the scoreboard based upon total score and then by ID
          2. Arrange the display data and then return
         */
        return gameIdScoreBoardMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(games -> games.getValue().values().stream().reduce(0, Integer::sum))))
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .map(entry -> {
                    Map<String, Integer> gameScores = entry.getValue(); // entries are like Mexico - 0, Canada - 5
                    List<String> displayData = new ArrayList<>(2);
                    for(String key : gameScores.keySet()) {
                        displayData.add(key + "-" + gameScores.get(key));
                    }
                    return String.join(", ", displayData);
                }).collect(Collectors.toCollection(ArrayList::new));
    }

}
