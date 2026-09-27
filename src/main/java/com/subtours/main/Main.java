package com.subtours.main;

import jakarta.transaction.UserTransaction;

import java.util.List;

import com.subtours.infra.JpaUtil;
import com.subtours.model.Equipamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        UserTransaction tx = com.arjuna.ats.jta.UserTransaction.userTransaction();
        EntityManagerFactory emf = null;
        EntityManager em  = null;
        
        try {
            emf = JpaUtil.criarEntityManagerFactory();
            em = emf.createEntityManager();

            CargaInicial.carregar(tx, em);

            em = emf.createEntityManager();

            // System.out.println("Conexão concluída!");
            List <Equipamento> equipamentos = em.createNamedQuery("Equipamento.todos", Equipamento.class).getResultList();

            for (Equipamento e : equipamentos){
                System.out.println(e.getNome());
            }

        } catch (Exception e) {
            System.out.println("Erro ao iniciar o JPA:");
            e.printStackTrace();

        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}