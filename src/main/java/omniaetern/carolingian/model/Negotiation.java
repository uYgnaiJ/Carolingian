package omniaetern.carolingian.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Negotiation {
    private UUID id;
    private String name;
    private Character initiator;
    private Character receiver;
    private Date initiateDate;

    private int round = 0;

    private List<Term> demands;
    private List<Term> offers;

    public Negotiation(String name, Character initiator, Character receiver, Date initiateDate,
    List<Term> demands, List<Term> offers){
        id = UUID.randomUUID();
        this.initiateDate = initiateDate;
        this.initiator = initiator;
        this.receiver = receiver;
        this.demands = demands;
        this.offers = offers;
    }

    public void editTerm(boolean isInitiator, List<Term> demands, List<Term> offers){
        this.demands = demands;
        this.offers = offers;
        round++;
    }

    public class Term{
        private TermType type;
        private int amount;
        private Character from;
        private Character to;
        private int round;

        private Term(TermType type, int amount, Character from, Character to, int round){
            this.type = type;
            this.amount = amount;
            this.from = from;
            this.to = to;
            this.round = round;
        }
    }

    public enum TermType{
        TITLE,
        CASH,
        ;
    }

    public UUID getId(){return id;}

    public Character getInitiator(){
        return initiator;
    }
    public Character getReceiver(){
        return receiver;
    }
}
