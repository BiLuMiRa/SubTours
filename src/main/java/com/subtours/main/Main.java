package com.subtours.main;

import jakarta.transaction.UserTransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.subtours.enums.situacaoExpedicaoEnum;
import com.subtours.enums.situacaoOperacionalEquipamentoEnum;
import com.subtours.infra.JpaUtil;
import com.subtours.model.AmostraCientifica;
import com.subtours.model.ColetaCientifica;
import com.subtours.model.Equipamento;
import com.subtours.model.Expedicao;
import com.subtours.model.Participacao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

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

//---------------- Consulta Expedições por período e situação
            LocalDateTime inicio = LocalDateTime.of(2026, 9, 28, 0, 0, 0);
            LocalDateTime termino = LocalDateTime.of(2026, 9, 28, 23, 59, 59);
            situacaoExpedicaoEnum situacao = situacaoExpedicaoEnum.planejada;
            TypedQuery<Object[]> queryExpedicaoPeriodoSituacao =
                em.createNamedQuery("Expedicoes.porPeriodoSituacao", Object[].class);
            queryExpedicaoPeriodoSituacao.setParameter("inicio", inicio);
            queryExpedicaoPeriodoSituacao.setParameter("termino", termino);
            queryExpedicaoPeriodoSituacao.setParameter("situacao", situacao);

            List<Object[]> resultados = queryExpedicaoPeriodoSituacao.getResultList();
            System.out.println("=== DETALHES DAS EXPEDIÇÕES ===");
            for (Object[] resultado : resultados) {
                System.out.println("ID: " + resultado[0]);
                System.out.println("Título: " + resultado[1]);
                System.out.println("ID Caverna: " + resultado[2]);
                System.out.println("Nome Caverna: " + resultado[3]);
                System.out.println("Início: " + resultado[4]);
                System.out.println("Término: " + resultado[5]);
                System.out.println("Situação: " + resultado[6]);
            }

// --------------- Consulta expedição selecionada, incluindo participantes e seus papéis
            TypedQuery<Expedicao> queryExpedicaoParticipantes = 
                em.createNamedQuery("Expedicao.selecionada", Expedicao.class);
            queryExpedicaoParticipantes.setParameter("id", 1);

            Expedicao expedicao = queryExpedicaoParticipantes.getSingleResult();
            System.out.println("=== EXPEDIÇÃO E PARTICIPANTES ===");
            System.out.println("ID: " + expedicao.getId());
            System.out.println("Título: " + expedicao.getTitulo());
            System.out.println("Objetivo: " + expedicao.getObjetivo());
            System.out.println("Início: " + expedicao.getInicio());
            System.out.println("Término: " + expedicao.getTermino());
            System.out.println("Orçamento: " + expedicao.getOrcamento());
            System.out.println("Custo: " + expedicao.getCusto());
            System.out.println("Quantidade de participantes: " + expedicao.getQntdParticip());
            System.out.println("Situação: " + expedicao.getSituacao());
            System.out.println("Cancelamento emergencial: " + expedicao.isCancelEmerg());

            for (Participacao participacao : expedicao.getParticipacoes()) {
                System.out.println(
                    "Participante: " + participacao.getPessoa().getNome()
                    + " | Papel: " + participacao.getPapelExpedicao()
                );
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