package dilemma.strategy;

import dilemma.Decision;

public class CooperateStrategy implements PrisonerStrategy {
    @Override
    public Decision decide() {
        return Decision.COOPERATE;
    }

    @Override
    public String getName() {
        return "Always cooperate";
    }

    @Override
    public void resetOpponentHistory() {

    }

    @Override
    public void storeOpponentHistory( Decision opponentDecision) {

    }
}
