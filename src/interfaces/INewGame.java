package interfaces;

import models.Scenario;

/*
Interface for accessing the active scenario in NewGame
 */
public interface INewGame {

    Scenario getActiveScenario();

    void nextScenario(String nextScenarioId);
}
