package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Scenario;

public interface IActiveScenario {

    Scenario getActiveScenario();

    void nextScenario(String nextScenarioId);
}
