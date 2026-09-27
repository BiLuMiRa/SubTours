package com.subtours.main;

import com.subtours.infra.JpaUtil;

import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = JpaUtil.criarEntityManagerFactory();
        
        try {

            System.out.println("Conexão concluída!");

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