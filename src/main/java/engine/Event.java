package engine;

import model.Character;

import java.util.Map;

public class Event {
    public String title;
    public String description;
    public Map<String, Character> relatedCharacters;
    public Runnable runnable;

    public Event(String title, String description, Map<String, Character> relatedCharacters, Runnable runnable){
        this.title = title;
        this.description = description;
        this.relatedCharacters = relatedCharacters;
        this.runnable = runnable;
    }

    public void run(){
        runnable.run();
    }
}
