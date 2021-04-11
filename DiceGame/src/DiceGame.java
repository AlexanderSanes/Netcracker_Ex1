import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DiceGame {
    private int numOfPlayers;
    private List<Player> players;
    private int numOfDice;
    private int gamesCounter;

    public DiceGame(List<String> players, int numOfDice) {
        this.gamesCounter = 0;
        this.numOfDice = numOfDice;
        this.numOfPlayers = players.size();
        this.players = new ArrayList<>();
        System.out.println("Number of dine: " + numOfDice);
        for (int i = 0; i < this.numOfPlayers; i++) {
            this.players.add(new Player(players.get(i)));
        }
    }

    public int rollDice() {
        int score = 0;
        for (int i = 0; i < numOfDice; i++) {
            score += (int) (Math.random() * 6) + 1;
        }
        return score;
    }

    public void printInfo() {
        System.out.println("***********Round " + gamesCounter + "***********");
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println(players.get(i).name + ": \n" +
                    "   current score: " + players.get(i).score +
                    "\n   wins: " + players.get(i).winsCounter);
        }
        System.out.println("*****************************");
    }

    public void startGame() {
        boolean f = true;
        while (f) {
            gamesCounter++;
            for (int i = 0; i < numOfPlayers; i++) {
                if (players.get(i).name == "You") {
                    System.out.println("Your turn! Roll the dice.(Press ENTER, for example)");
                    Scanner scanner = new Scanner(System.in);
                    String tap = scanner.nextLine();
                    players.get(i).setScore(rollDice());
                    System.out.println("Your score is: " + players.get(i).score);
                } else {
                    players.get(i).setScore(rollDice());
                    //System.out.println(players.get(i).name + "'s score is: " + players.get(i).score);
                }
            }
            Collections.sort(players);
            System.out.println("Winer of this round is " + players.get(0).name);
            players.get(0).incWinsCounter();
            printInfo();
            if (players.get(0).winsCounter == 7) {
                System.out.println("End game. Winer is: " + players.get(0).name);
                f = false;
            }
        }

    }

    public static void main(String[] args) {
        List<String> players = new ArrayList<>();
        players.add("You");
        players.add("Tom");
        players.add("John");
        players.add("Bot");
        DiceGame game1 = new DiceGame(players, 3);
        game1.startGame();
    }


    class Player implements Comparable<Player> {
        private String name;
        private int score;
        private int winsCounter;

        public Player(String name) {
            this.name = name;
            score = 0;
            winsCounter = 0;
        }

        public String getName() {
            return this.name;
        }

        public int getScore() {
            return this.score;
        }

        public int getWinsCounter() {
            return this.winsCounter;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void incWinsCounter() {
            this.winsCounter++;
        }

        @Override
        public int compareTo(Player player) {
            if (player.score < this.score) {
                return -1;
            } else if (player.score > this.score) {
                return 1;
            } else {
                return 0;
            }
        }


    }

}


