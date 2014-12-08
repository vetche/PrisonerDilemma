package dilemma;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStatistics {

    private Map<String, StrategyStatistics> getStatistics(List<Prisoner> prisoners) {
        Map<String, StrategyStatistics> strategies = new HashMap<String, StrategyStatistics>();

        for (Prisoner p : prisoners) {
            String strategyName = p.getStrategyName();
            int prisonerScore = p.getScore();

            if (!strategies.containsKey(strategyName)) {
                strategies.put(strategyName, new StrategyStatistics(prisonerScore));
                continue;
            }

            StrategyStatistics row = strategies.get(strategyName);
            row.updateCount();
            row.updateAverageScore(prisonerScore);
        }

        return strategies;
    }

    public void printStatistics(List<Prisoner> prisoners) {
        for (Map.Entry<String, StrategyStatistics> e : getStatistics(prisoners).entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue().getCount() + " times, with average score " + e.getValue().getAverageScore());
        }
    }
}
