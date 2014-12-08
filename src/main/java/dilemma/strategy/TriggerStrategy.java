package dilemma.strategy;

import dilemma.Decision;

import java.util.ArrayList;
import java.util.List;


public class TriggerStrategy implements PrisonerStrategy {
    List<Decision> opponentDecisionHistory = new ArrayList<Decision>();

    @Override
    public Decision decide() {
        return chooseMode();
    }

    @Override
    public String getName() {
        return "Trigger strategy";
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
        if( opponentDecisionHistory.size() == 0){
            return Decision.COOPERATE;
        }

        if( opponentDecisionHistory.contains( Decision.DEFECT)){
            return Decision.DEFECT;
        }

        return Decision.COOPERATE;
    }
}
