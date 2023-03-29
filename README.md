# sportradar

Below assumptions were made
Since there are several games, I am considering this as a tournament.

Inside this tournament there will be several games played.

1. To start a game, user needs to send only the homeTeam and the awayTeam
2. to Update the score, user must send the gameID, homeTeamScore, awayTeamScore
3. To finish the existing game, user must send the gameID so that we can know which game is finishing
4. To view the score, just invoke the getSummary() - It will display the results as expected from the exercise.
5. GameMain contains the main method which invokes the entire flow. Since input is taken from user, I have specified the teams, updated the scores and finally fetching the summary.
All the code has been modularized into smaller chunks of appropriate classes if we want to develop the application in a full fledged manner.
