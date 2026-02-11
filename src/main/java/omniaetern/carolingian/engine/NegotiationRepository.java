package omniaetern.carolingian.engine;

import omniaetern.carolingian.model.Character;
import omniaetern.carolingian.model.Negotiation;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NegotiationRepository {
    public static final NegotiationRepository NEGOTIATION_REPOSITORY = new NegotiationRepository();

    private final ConcurrentMap<UUID, Negotiation> byId = new ConcurrentHashMap<>();
    private final ConcurrentMap<Character, Set<UUID>> byInitiator = new ConcurrentHashMap<>();
    private final ConcurrentMap<Character, Set<UUID>> byReceiver = new ConcurrentHashMap<>();

    private NegotiationRepository(){}

    public Negotiation get(UUID id){
        return byId.get(id);
    }

    public List<Negotiation> getByInitiator(Character character){
        Set<UUID> ids = byInitiator.get(character);
        return ids.stream().map(byId::get).toList();
    }

    public void insert(Negotiation negotiation) {
        if (negotiation.getId()==null){
            throw new IllegalArgumentException();
        }
        if (byId.containsKey(negotiation.getId())) {
            unindex(negotiation);
        }
        byId.put(negotiation.getId(), negotiation);
        index(negotiation);
    }

    private void index(Negotiation n) {
        add(byInitiator, n.getInitiator(), n.getId());
        add(byReceiver,  n.getReceiver(),  n.getId());
    }

    private void unindex(Negotiation n) {
        remove(byInitiator, n.getInitiator(), n.getId());
        remove(byReceiver,  n.getReceiver(),  n.getId());
    }

    private static void add(ConcurrentMap<Character, Set<UUID>> idx, Character key, UUID id) {
        if (key == null) return;
        idx.computeIfAbsent(key, k -> ConcurrentHashMap.newKeySet()).add(id);
    }

    private static void remove(ConcurrentMap<Character, Set<UUID>> idx, Character key, UUID id) {
        if (key == null) return;
        idx.computeIfPresent(key, (k, set) -> {
            set.remove(id);
            return set.isEmpty() ? null : set;
        });
    }
}
