package com.subtours.repository;

import java.time.LocalDate;
import java.util.List;

import com.subtours.model.Caverna;

import jakarta.persistence.EntityManager;

public class CavernaRepository {
    
    private EntityManager em;

    public CavernaRepository(EntityManager em) {
        this.em = em;
    }

    public void cavernasAcessiveis() {
        List<Caverna> namedQueryCaverna = em.createNamedQuery("Caverna.buscarAcessiveis", Caverna.class).getResultList();

        System.out.println("=== Cavernas Acessíveis ===");
        for(Caverna c : namedQueryCaverna) {
            System.out.println("Codigo de cadastro amebiental: "+ c.getCodCadAmbiental() + "\nNome: " + c.getNomeCaverna() + "\nMunicipio: " + c.getMunicipio() + "\nEstado: " + c.getUf() + "\n");
        }
    }

    public void cavernaInspVencida(LocalDate dataLimite) {
        List<Caverna> namedQueryCavernas = em.createNamedQuery("Caverna.buscarInspecaoVencida", Caverna.class).setParameter("dataLimite", dataLimite).getResultList();

        System.out.println("=== Cavernas com inspeção vencida ===");

        for(Caverna c : namedQueryCavernas) {
            System.out.println("Codigo de cadastro amebiental: "+ c.getCodCadAmbiental() + "\nNome: " + c.getNomeCaverna() + "\nMunicipio: " + c.getMunicipio() + "\nEstado: " + c.getUf() + "\nData última inspeção: " + c.getUltimaInsp() + "\n");
        }
    }


}
