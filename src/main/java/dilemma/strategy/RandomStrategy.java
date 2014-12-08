package dilemma.strategy;

import dilemma.Decision;
import java.util.Random;

public class RandomStrategy implements PrisonerStrategy {
    private Random random;

    public RandomStrategy() {
        random = new Random(System.currentTimeMillis());
    }
    @Override
    public Decision decide() {
        return (random.nextInt() % 2 == 0) ? Decision.COOPERATE : Decision.DEFECT;
    }

    @Override
    public String getName() {
        return "Choose at random";
    }

    @Override
    public void resetOpponentHistory() {

    }

    @Override
    public void storeOpponentHistory(Decision opponentDecision) {

    }
}
