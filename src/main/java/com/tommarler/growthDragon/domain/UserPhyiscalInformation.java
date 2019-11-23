package com.tommarler.growthDragon.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userPhyiscalInformation")
public class UserPhyiscalInformation {

    @Id
    private String id;
    private User user;

}
