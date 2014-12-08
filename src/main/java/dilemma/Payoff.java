package dilemma;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import java.util.List;

public class Payoff {
    private Table<Decision, Decision, Integer> payoffs;

    public Payoff() {
        payoffs = createTable();
        payoffs.put(Decision.COOPERATE, Decision.COOPERATE, 1);
        payoffs.put(Decision.COOPERATE, Decision.DEFECT, -2);
        payoffs.put(Decision.DEFECT, Decision.COOPERATE, 2);
        payoffs.put(Decision.DEFECT, Decision.DEFECT, -1);
    }

    private Table<Decision, Decision, Integer> createTable() {
        List<Decision> rowList = Lists.newArrayList();
        rowList.add(Decision.COOPERATE);
        rowList.add(Decision.DEFECT);

        List<Decision> columnList = Lists.newArrayList();
        columnList.add(Decision.COOPERATE);
        columnList.add(Decision.DEFECT);

        Table<Decision, Decision, Integer> table = ArrayTable.create(rowList, columnList);

        return table;
    }

    public Integer getPayoff(Decision prisoner1, Decision prisoner2) {
        return payoffs.get(prisoner1, prisoner2);
    }
}
