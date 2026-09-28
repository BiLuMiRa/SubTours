package com.subtours.repository;

import java.time.LocalDate;
import java.util.List;

import com.subtours.model.Expedicao;

import jakarta.persistence.EntityManager;

public class ExpedicaoRepository {
    private EntityManager em;

    public ExpedicaoRepository (EntityManager em) {
        this.em = em;
    }
}
