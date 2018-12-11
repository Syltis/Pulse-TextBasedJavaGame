package Interfaces;

import Models.Scenario;

public interface INewGame {

    Scenario getActiveScenario();

    void nextScenario(String nextScenarioId);
}
