package model;

import model.contract.Contract;
import model.title.Title;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Character {
    private UUID id;
    private boolean isPlayer;

    private String name;
    private Family family;
    private Dynasty dynasty;

    private Religion religion;
    private Culture culture;

    private List<Title> titles;
    private List<Title> claims;

    private Map<Character, Contract> vassalCharacters;
    private Map<Family, Contract> vassalFamilies;

    private int cash;
    private int debt;
}
