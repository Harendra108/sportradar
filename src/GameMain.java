package src;

import java.util.List;

public class GameMain {
    public static void main(String[] args) {
        Tournament tournament = new Tournament();

        Team homeTeam = new Team("Mexico", 1);
        Team awayTeam = new Team("Canada", 2);
        tournament.startGame(homeTeam, awayTeam);
        tournament.updateScore(1, 0, 5);

        Team spainHomeTeam = new Team("Spain", 3);
        Team brazilAwayTeam = new Team("Brazil", 4);
        tournament.startGame(spainHomeTeam, brazilAwayTeam);
        tournament.updateScore(2, 10, 2);

        Team germanyHomeTeam = new Team("Germany", 5);
        Team franceAwayTeam = new Team("France", 6);
        tournament.startGame(germanyHomeTeam, franceAwayTeam);
        tournament.updateScore(3, 2, 2);

        Team uruguayHomeTeam = new Team("Uruguay", 7);
        Team italyAwayTeam = new Team("Italy", 8);
        tournament.startGame(uruguayHomeTeam, italyAwayTeam);
        tournament.updateScore(4, 6, 6);

        Team argentinaHomeTeam = new Team("Argentina", 9);
        Team australiaAwayTeam = new Team("Australia", 10);
        tournament.startGame(argentinaHomeTeam, australiaAwayTeam);
        tournament.updateScore(5, 3, 1);

        tournament.finishGame(1);
        tournament.finishGame(2);
        tournament.finishGame(3);
        tournament.finishGame(4);
        tournament.finishGame(5);

        tournament.finishTournament();

        List<String> summary = tournament.getSummary();
        for(String str : summary) {
            System.out.println(str);
        }

    }
}
