package omniaetern.carolingian.engine;

import omniaetern.carolingian.model.Character;
import omniaetern.carolingian.model.Negotiation;
import omniaetern.carolingian.model.War;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class LogicController {
    public static final LogicController LOGIC_CONTROLLER = new LogicController();

    private LogicController(){}

    private final CharacterRepository characterRepository = CharacterRepository.CHARACTER_REPOSITORY;
    private final NegotiationRepository negotiationRepository = NegotiationRepository.NEGOTIATION_REPOSITORY;
    private final WarRepository warRepository = WarRepository.WAR_REPOSITORY;

    public void initiateNegotiation(Character initiator, Character receiver, String name, Date initiateDate,
                                    List<Negotiation.Term> demands, List<Negotiation.Term> offers){
        negotiationRepository.insert(new Negotiation(name, initiator, receiver, initiateDate, demands, offers));
    }

    public List<Negotiation> getNegotiationByInitiator(Character character){
        return negotiationRepository.getByInitiator(character);
    }

    public Character getCharacter(UUID id){
        return characterRepository.get(id);
    }

    public void initiateWar(String name, LocalDate startDate, LocalDate endDate, Character initiator, Character receiver, Negotiation negotiation){
        warRepository.insert(new War(name, startDate, endDate, initiator, receiver, negotiation));
    }
}
