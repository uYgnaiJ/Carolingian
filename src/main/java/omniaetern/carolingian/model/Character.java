package omniaetern.carolingian.model;

import omniaetern.carolingian.model.contract.Contract;
import omniaetern.carolingian.model.title.Title;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Character {
    private UUID id;
    private Boolean isPlayer;

    private String name;
    private Family family;
    private Dynasty dynasty;

    private Religion religion;
    private Culture culture;

    private List<Title> titles;
    private List<Title> claims;

    private Map<Character, Contract> vassalCharacters;
    private Map<Family, Contract> vassalFamilies;

    private Integer cash;
    private Integer debt;

    public Character(){}

    public UUID getId(){
        return id;
    }

    public List<Title> getTitles(){
        return titles;
    }

    public List<Title> getClaims(){
        return claims;
    }
}
