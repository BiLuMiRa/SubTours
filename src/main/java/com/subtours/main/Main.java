package com.subtours.main;

import jakarta.transaction.UserTransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.subtours.enums.situacaoOperacionalEquipamentoEnum;
import com.subtours.infra.JpaUtil;
import com.subtours.model.AmostraCientifica;
import com.subtours.model.ColetaCientifica;
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
            // List <Equipamento> equipamentos = em.createNamedQuery("Equipamento.todos", Equipamento.class).getResultList();

            // for (Equipamento e : equipamentos){
            //     System.out.println(e.getNome());
            // }

            // List <ColetaCientifica> coletas = em.createNamedQuery("ColetaCientifica.PorIdExpedicao", ColetaCientifica.class)
            //     .setParameter("id", 2)
            //     .getResultList();

            // for (ColetaCientifica cc : coletas){
            //     System.out.println(cc.getDescricaoPonto() + "setor:" + cc.getSetor().getDenominacao() + "pesquisador: " + cc.getPesquisador().getNome());
            // }

            // ColetaCientifica cc = em.getReference(ColetaCientifica.class, 1);
            // List <AmostraCientifica> amostras = em.createNamedQuery("Amostras.PorIdColeta", AmostraCientifica.class)
            //     .setParameter("id", cc.getId())
            //     .getResultList();

            // for (AmostraCientifica a : amostras){
            //     System.out.println(a.getCodAmostra() + " : " + a.getColeta().getId());
            // }

            LocalDate dataInicio = LocalDate.of(2026, 10, 1);
            LocalDateTime dataFim = LocalDateTime.of(2026, 10, 10, 8, 0);
            List <Equipamento> equipamentos = em.createNamedQuery("Equipamentos.buscaSituacaoPorData", Equipamento.class)
                .setParameter("situacao", situacaoOperacionalEquipamentoEnum.disponivel)
                .setParameter("dataInicio", dataInicio)
                .setParameter("dataFim", dataFim)
                .getResultList();
            
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