package src;

import java.util.List;

public class GameMain {
    public static void main(String[] args) {
        Tournament tournament = new Tournament();
        Team homeTeam = new Team();
        Team awayTeam = new Team();
        tournament.startGame(homeTeam, awayTeam);
        tournament.updateScore(1, 2, 4);
        tournament.finishGame(1);
        List<String> summary = tournament.getSummary();

        System.out.println(summary);


    }
}
