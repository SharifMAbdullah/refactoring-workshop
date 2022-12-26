package RefactorPractice.Trivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TriviaJudgement {

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];


    public TriviaJudgement() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    protected void announce(Object message) {
        System.out.println(message);
    }

    //duplicate code
    protected void askQuestion() {
        ArrayList<String> category = new ArrayList<>(Arrays.asList("Pop", "Science", "Sports"));
        for (String iterator: category) {
            if (currentCategory().equals(iterator))
                announce(popQuestions.removeFirst());
            else if (currentCategory().equals(iterator))
                announce(scienceQuestions.removeFirst());
            else if(currentCategory().equals(iterator))
                announce(sportsQuestions.removeFirst());
                //Unnecessary method
            else announce(rockQuestions.removeFirst());
        }
    }

    //long method, duplicate code
    protected String currentCategory() {
        int pop[] = {0,4,8};
        int science[] = {1,5,9};
        int sports[] = {2,6,10};
        for (int iterator: places) {
            if (places[currentPlayer] == pop[iterator])
                return "Pop";
            else if (places[currentPlayer] == science[iterator])
                return "Science";
            else if (places[currentPlayer] == sports[iterator])
                return "Sports";
            else return "Rock";
        }
        return null;
    }

    public void announceWinner(){
        announce("Answer was correct!!!!");
        purses[currentPlayer]++;
        announce(players.get(currentPlayer)+ " now has " + purses[currentPlayer] + " Gold Coins.");
    }
    public void resetPlayers(){
        if (currentPlayer == players.size())
            currentPlayer = 0;
    }
    public boolean declareWinner(){
        announceWinner();
        boolean winner = didPlayerWin();
        currentPlayer++;
        resetPlayers();
        return winner;
    }
    //long method
    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                declareWinner();
            } else {
                currentPlayer++;
                resetPlayers();
                return true;
            }
        } else {
            declareWinner();
        }
        return false;
    }

    //long method
    public boolean wrongAnswer() {
        announce("Question was incorrectly answered");
        announce(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;
        currentPlayer++;
        resetPlayers();
        return true;
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }

}
