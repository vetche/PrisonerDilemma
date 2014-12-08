package dilemma.strategy;

import dilemma.Decision;

public class DefectStrategy implements PrisonerStrategy {
    public Decision decide(){
        return Decision.DEFECT;
    }

    @Override
    public String getName() {
        return "Always defect";
    }

    @Override
    public void resetOpponentHistory() {

    }

    @Override
    public void storeOpponentHistory(Decision opponentDecision) {

    }
}
