package omniaetern.carolingian.engine;

import omniaetern.carolingian.model.Character;
import static omniaetern.carolingian.ExceptionHandler.handle;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CharacterRepository {
    public static final CharacterRepository CHARACTER_REPOSITORY = new CharacterRepository();

    private final ConcurrentMap<UUID, Character> byId = new ConcurrentHashMap<>();

    private CharacterRepository(){}

    public Character get(UUID id){
        return byId.get(id);
    }

    public void insert(Character character){
        byId.put(character.getId(), character);
    }

    public void loadFromFile(String path){
        try{
            File file = new File(path);

        } catch (Exception e) {
            handle(e);
        }
    }
}
