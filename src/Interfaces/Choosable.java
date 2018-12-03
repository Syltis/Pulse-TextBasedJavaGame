package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Scenario;

public interface Choosable {

    Scenario getActiveScenario();

    void nextScenario(String nextScenarioId);
}
