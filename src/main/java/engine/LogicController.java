package engine;

import model.Character;
import model.Negotiation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LogicController {
    public static final LogicController LOGIC_CONTROLLER = new LogicController();

    public LogicController(){}

    private final CharacterRepository characterRepository = CharacterRepository.CHARACTER_REPOSITORY;
    private final NegotiationRepository negotiationRepository = NegotiationRepository.NEGOTIATION_REPOSITORY;
    private final Map<UUID, War> warMap = new ConcurrentHashMap<>();

    public void initiateNegotiation(Character initiator, Character receiver, String name, Date initiateDate,
                                    List<Negotiation.Term> demands, List<Negotiation.Term> offers){
        negotiationRepository.insert(new Negotiation(name, initiator, receiver, initiateDate, demands, offers));
    }

    public List<Negotiation> getNegotiationByInitiator(Character character){
        return negotiationRepository.getByInitiator(character);
    }

    public void initiateWar(String name, LocalDate startDate, LocalDate endDate, Character initiator, Character receiver, Negotiation negotiation){
        War war = new War(name, startDate, endDate, initiator, receiver, negotiation);
        warMap.put(war.getId(), war);
    }
}
