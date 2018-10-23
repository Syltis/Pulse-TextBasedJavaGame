package Gameplay;

import GUI.GameWindow;

/*
- This could be used as a way of making the story segments into objects. Depends on how we write our story.
 */
public class Situation {


    private String location;
    private String description;
    GameWindow gameWindow;
    Choice choice;

    {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
