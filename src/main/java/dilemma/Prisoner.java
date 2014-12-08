package dilemma;

import dilemma.strategy.PrisonerStrategy;

import java.util.UUID;

public class Prisoner {
    private String name;
    private PrisonerStrategy strategy;
    private Integer score = 100;

    public Prisoner(PrisonerStrategy strategy) {
        this.name = UUID.randomUUID().toString();
        this.strategy = strategy;
    }

    public Prisoner( String name, PrisonerStrategy strategy ){
        this.name = name;
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return name;
    }

    public Decision decide() {
        return strategy.decide();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Prisoner && name.equals(((Prisoner) obj).name);
    }

    public void updateScore(Integer payoff) {
        if (payoff != null && payoff != 0) {
            score += payoff;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrisonerStrategy getStrategy() {
        return strategy;
    }

    public void setScore( int newScore) {
        score = newScore;
    }

    public Integer getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getStrategyName() {
        return strategy.getName();
    }

    public void resetOpponentHistory() {
        strategy.resetOpponentHistory();
    }

    public void updateOpponentHistory(Decision opponentDecision) {
        strategy.storeOpponentHistory(opponentDecision);
    }
}