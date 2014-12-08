package dilemma.strategy;

import dilemma.Decision;

import java.util.Random;


public class MirrorStrategy implements PrisonerStrategy {
    Random random = new Random(System.currentTimeMillis());
    Decision lastOpponentDecision = (random.nextInt() % 2 == 0) ? Decision.COOPERATE : Decision.DEFECT;

    @Override
    public Decision decide() {
        return lastOpponentDecision;
    }

    @Override
    public String getName() {
        return "Mirror strategy";
    }

    @Override
    public void resetOpponentHistory() {
        lastOpponentDecision = (random.nextInt() % 2 == 0) ? Decision.COOPERATE : Decision.DEFECT;
    }

    @Override
    public void storeOpponentHistory(Decision opponentDecision) {
        lastOpponentDecision = opponentDecision;
    }
}
