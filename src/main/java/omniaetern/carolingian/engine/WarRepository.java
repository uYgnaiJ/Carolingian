package omniaetern.carolingian.engine;

import omniaetern.carolingian.model.War;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WarRepository {
    public static final WarRepository WAR_REPOSITORY = new WarRepository();
    private WarRepository(){}

    private final ConcurrentMap<UUID, War> byId = new ConcurrentHashMap<>();

    public void insert(War war) {
        byId.put(war.getId(), war);
    }

    public War get(UUID id){
        return byId.get(id);
    }
}
