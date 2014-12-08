package dilemma.strategy;

import dilemma.Decision;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TitForTatStrategy implements PrisonerStrategy {
    List<Decision> opponentDecisionHistory = new ArrayList<Decision>();
    Decision lastDecision;
    Random random = new Random(System.currentTimeMillis());

    @Override
    public Decision decide() {
        Decision decided = chooseMode();

        lastDecision = decided;
        return decided;
    }

    @Override
    public String getName() {
        return "Tit for tat";
    }

    @Override
    public void resetOpponentHistory() {
        opponentDecisionHistory.clear();
    }

    @Override
    public void storeOpponentHistory(Decision opponentDecision) {
        opponentDecisionHistory.add(opponentDecision);
    }

    private Decision chooseMode(){
        if( lastDecision == Decision.DEFECT ){
            return Decision.COOPERATE;
        }

        if( opponentDecisionHistory.size() == 0){
            return (random.nextInt() % 2 == 0) ? Decision.COOPERATE : Decision.DEFECT;
        }

        if( opponentDecisionHistory.get( opponentDecisionHistory.size() - 1 ) == Decision.DEFECT){
            return Decision.DEFECT;
        }

        return Decision.COOPERATE;
    }
}