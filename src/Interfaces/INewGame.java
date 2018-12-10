package Interfaces;

/*
Has edited this:
- Kristoffer
*/

import Models.Scenario;

public interface INewGame {

    Scenario getActiveScenario();

    void nextScenario(String nextScenarioId);
}
