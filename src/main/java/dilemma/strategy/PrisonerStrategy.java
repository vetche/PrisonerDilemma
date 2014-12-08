package dilemma.strategy;

import dilemma.Decision;

public interface PrisonerStrategy {
    Decision decide();

    String getName();

    void resetOpponentHistory();

    void storeOpponentHistory(Decision opponentDecision);
}
