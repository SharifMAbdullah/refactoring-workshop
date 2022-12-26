package RefactorPractice.Trivia;

import java.util.ArrayList;

public class TriviaPlayers extends TriviaJudgement{

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public void newPlayerAnnouncement(String playerName){
        announce(playerName + " was added");
        announce("They are player number " + players.size());
    }

    //long method
    public boolean add(String playerName) {

        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        newPlayerAnnouncement(playerName);
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void rollAnnouncement(int roll){
        announce(players.get(currentPlayer) + " is the current player");
        announce("They have rolled a " + roll);
    }
    public void newQuestionsLocationAnnouncement(){
        announce(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
        announce("The category is " + currentCategory());
        askQuestion();
    }

    //long method
    public void penaltyBoxAction(int roll){
        if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            announce(players.get(currentPlayer) + " is getting out of the penalty box");
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11)
                places[currentPlayer] = places[currentPlayer] - 12;
            newQuestionsLocationAnnouncement();
        } else {
            announce(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
        }
    }

    //long method
    public void roll(int roll) {
        rollAnnouncement(roll);
        if (inPenaltyBox[currentPlayer]) {
            penaltyBoxAction(roll);
        } else {

            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
            newQuestionsLocationAnnouncement();
        }
    }
}
