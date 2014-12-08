
package dilemma;

import com.rits.cloning.Cloner;
import dilemma.strategy.*;

import java.util.*;

public class Game {
    private List<Prisoner> prisoners;
    private List<Prisoner> unsuccessfulPrisoners = new ArrayList<Prisoner>();
    private List<Prisoner> weakPrisoners = new ArrayList<Prisoner>();
    private List<Class<? extends PrisonerStrategy>> strategies;
    private Random random;
    private Payoff payoff;
    private Cloner cloner;

    private Integer initialPopulation = 200;
    private Integer numberOfRounds = 5;
    private Integer scoreThresholdForCloning = 1000;

    public Game() {
        cloner = new Cloner();
        random = new Random(System.currentTimeMillis());
        strategies = initializeStrategies();
        prisoners = initializePrisoners();
        payoff = new Payoff();
    }

    private List<Prisoner> initializePrisoners() {
        List<Prisoner> prisonerList = new ArrayList<Prisoner>();

        for (int i = 0; i < initialPopulation; i++) {
            prisonerList.add(new Prisoner(getRandomStrategy()));
        }

        return prisonerList;
    }

    private List<Class<? extends PrisonerStrategy>> initializeStrategies() {
        List<Class<? extends PrisonerStrategy>> strategyList = new ArrayList<Class<? extends PrisonerStrategy>>();

        strategyList.add(DefectStrategy.class);
        strategyList.add(CooperateStrategy.class);
        strategyList.add(RandomStrategy.class);
        strategyList.add(TitForTatStrategy.class);
        strategyList.add(TriggerStrategy.class);
        strategyList.add(MirrorStrategy.class);

        return strategyList;
    }

    public void play() {
        if (prisoners.size() < 2) {
//            System.out.println("There are not enough prisoners to play a game.");
            return;
        }

        Prisoner prisoner1 = getRandomPrisoner();
        Prisoner prisoner2 = getRandomPrisoner();

        while (prisoner1.equals(prisoner2)) {
            prisoner2 = getRandomPrisoner();
        }

        prisoner1.resetOpponentHistory();
        prisoner2.resetOpponentHistory();

        for (int i = 0; i <= random.nextInt(numberOfRounds); i++) {
            Decision prisoner1Decision = prisoner1.decide();
            Decision prisoner2Decision = prisoner2.decide();

            prisoner1.updateScore(payoff.getPayoff(prisoner1Decision, prisoner2Decision));
            prisoner2.updateScore(payoff.getPayoff(prisoner2Decision, prisoner1Decision));

            prisoner1.updateOpponentHistory(prisoner2Decision);
            prisoner2.updateOpponentHistory(prisoner1Decision);
        }

        checkPrisonerState(prisoner1, prisoner2);
    }

    private void checkPrisonerState(Prisoner ... ps ){
        for( Prisoner p : ps ){
            if (!canPrisonerPlay(p)) {
                disablePrisoner(p);
            }

            if (canPrisonerSplit(p)) {
                splitPrisoner(p);
            }
        }
    }

    private void disablePrisoner(Prisoner prisoner) {
        unsuccessfulPrisoners.add(prisoner);
        prisoners.remove(prisoner);
    }

    private boolean canPrisonerPlay(Prisoner prisoner) {
        return prisoner.getScore() > 0;
    }

    private void splitPrisoner(Prisoner prisoner) {
        int newScore = prisoner.getScore() / 2;

        disableWorstPrisoner(newScore);
        prisoner.setScore(newScore);

        PrisonerStrategy strategy = prisoner.getStrategy();
        prisoners.add(new Prisoner(cloner.deepClone(strategy)));
    }

    private void disableWorstPrisoner(int thresholdScore) {
        Prisoner worstPrisoner = prisoners.get(0);

        for (Prisoner p : prisoners) {
            if (p.getScore() < worstPrisoner.getScore()) {
                worstPrisoner = p;
            }
        }

        if (worstPrisoner.getScore() < thresholdScore) {
            weakPrisoners.add(worstPrisoner);
            prisoners.remove(worstPrisoner);
        }
    }

    private boolean canPrisonerSplit(Prisoner prisoner) {
        return prisoner.getScore() > scoreThresholdForCloning;
    }

    private Prisoner getRandomPrisoner() {
        return prisoners.get(random.nextInt(prisoners.size()));
    }

    private PrisonerStrategy getRandomStrategy() {
        PrisonerStrategy selectedStrategy = new RandomStrategy();

        try {
            selectedStrategy = strategies.get(random.nextInt(strategies.size())).newInstance();
        } catch (Exception e) {
            System.err.println("Error occurred while instantiating strategy class.");
        }

        return selectedStrategy;
    }

    public void printStatistics() {
        GameStatistics statistics = new GameStatistics();

        if (prisoners.size() > 0) {
            System.out.println("Successful prisoners:");
            statistics.printStatistics(prisoners);
        }

        if (unsuccessfulPrisoners.size() > 0) {
            System.out.println();
            System.out.println("Unsuccessful prisoners:");
            statistics.printStatistics(unsuccessfulPrisoners);
        }

        if (weakPrisoners.size() > 0) {
            System.out.println();
            System.out.println("Weak prisoners:");
            statistics.printStatistics(weakPrisoners);
        }
    }
}