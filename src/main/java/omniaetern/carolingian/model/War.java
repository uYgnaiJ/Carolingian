package omniaetern.carolingian.model;

import java.time.LocalDate;
import java.util.UUID;

public class War {
    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Character initiator;
    private Character receiver;
    private Negotiation negotiation;

    public War(String name, LocalDate startDate, LocalDate endDate, Character initiator, Character receiver, Negotiation negotiation){
        id = UUID.randomUUID();
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.initiator = initiator;
        this.receiver = receiver;
        this.negotiation = negotiation;
    }

    public UUID getId(){
        return id;
    }
}
