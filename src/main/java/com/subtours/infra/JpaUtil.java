package com.subtours.infra;

import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;

public final class JpaUtil {

    private JpaUtil(){}
    
    public static EntityManagerFactory criarEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("SubToursPU");
    }
}
