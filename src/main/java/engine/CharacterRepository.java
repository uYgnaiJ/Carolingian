package engine;

import model.Character;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CharacterRepository {
    public static final CharacterRepository CHARACTER_REPOSITORY = new CharacterRepository();

    private final ConcurrentMap<UUID, Character> byId = new ConcurrentHashMap<>();

    public CharacterRepository(){}

    public Character get(UUID id){
        return byId.get(id);
    }
}
