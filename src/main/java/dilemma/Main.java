package dilemma;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        for (int i = 1; i <= 1000000; i++)
            game.play();

        game.printStatistics();
    }
}