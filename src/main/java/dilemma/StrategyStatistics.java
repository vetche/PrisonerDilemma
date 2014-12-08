package dilemma;

public class StrategyStatistics {
    private int count;
    private int averageScore;

    public StrategyStatistics(Integer initScore) {
        count = 1;
        averageScore = initScore;
    }

    public int getCount() {
        return count;
    }

    public void updateCount() {
        this.count += 1;
    }

    public int getAverageScore() {
        return averageScore / count;
    }

    public void updateAverageScore(int prisonerScore) {
        averageScore += prisonerScore;
    }
}
