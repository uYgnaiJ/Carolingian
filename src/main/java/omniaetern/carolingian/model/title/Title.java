package omniaetern.carolingian.model.title;

import omniaetern.carolingian.model.contract.Contract;

import java.util.Map;
import java.util.UUID;

public abstract class Title {
    private UUID id;
    private String name;

    private Map<LandTitle, Contract> subLandTitles;
    private Map<JobTitle, Contract> subJobTitles;
}
