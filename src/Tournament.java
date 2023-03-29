package src;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Tournament {
    private long tournamentID;
    private final List<Game> gamesList = new CopyOnWriteArrayList<>();
    private final String tournamentLocation = "Wroclaw";//For this test, I am marking it as default one
    private Team winnerTeam;
    private Team runnerUpTeam;
    private Team thirdPlaceTeam;//above three entries are there in case we want to know who is the winner, runnerUp and the bronze team
    private final Map<Integer, Map<String, Integer>> gameIdScoreBoardMap = new TreeMap<>();


    private static volatile boolean isTournamentStarted = false;

    public synchronized void startGame(Team homeTeam, Team awayTeam) {
        //gameID will be the autoIncrement of the current GameID.

        Optional<Game> gameWithLastID = gamesList.stream().max(Comparator.comparing(Game::getGameID));
        int gameID = gameWithLastID.map(Game::getGameID).orElse(0) + 1;
        Game game = new Game(gameID, homeTeam, awayTeam, GameStatus.STARTED, 0, 0);
        gamesList.add(game);
        if(!isTournamentStarted) {
            isTournamentStarted = true;
            Thread thread = new Thread(this::updateScoreBoard);
            thread.start();

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
            updateScoreBoardOfAllGames();
            if(!isTournamentStarted) {
                break;
            }
        }
    }

    private void updateScoreBoardOfAllGames() {
        synchronized (gamesList) {
            gamesList.forEach(game -> gameIdScoreBoardMap.put(game.getGameID(), game.getScore()));
        }

    }

    public void finishGame(int gameID) {
        Optional<Game> gameToRemove = gamesList.stream().filter(game -> game.getGameID() == gameID).findFirst();
        if(gameToRemove.isPresent()) {
            Game game = gameToRemove.get();
            game.setStatus(GameStatus.FINISHED);
            game.finishGame();
        }
    }

    public List<String> getSummary() {
        /*
          1. Sort the scoreboard based upon total score and then by ID
          2. Arrange the display data and then return
         */
        Comparator<Game> comparator = (g1, g2) -> {
          if(g1.getTotalScoreForTheGame() > g2.getTotalScoreForTheGame()) {
              return 1;
          } else if(g1.getTotalScoreForTheGame() < g2.getTotalScoreForTheGame()) {
              return -1;
          } else {
              return Integer.compare(g1.getGameID(), g2.getGameID());
          }
        };

        ArrayList<String> scoreSorted = gamesList.stream().sorted(Collections.reverseOrder(comparator))
                .map(Game::toString)
                .collect(Collectors.toCollection(ArrayList::new));

        return scoreSorted;



    }

}
