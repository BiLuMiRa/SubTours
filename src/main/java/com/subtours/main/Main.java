package com.subtours.main;

import jakarta.transaction.UserTransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.subtours.controller.GenericController;
import com.subtours.enums.*;
import com.subtours.infra.JpaUtil;
import com.subtours.model.*;
import com.subtours.repository.CavernaRepository;
import com.subtours.repository.ColetaCientificaRepository;
import com.subtours.repository.EquipamentoRepository;
import com.subtours.repository.ExpedicaoRepository;


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

            em.clear();

            //Coleta Científica
            ColetaCientificaRepository coletaRepo = new ColetaCientificaRepository(em);

            //Lista as coletas de uma expedição com setor e pesquisador reponsável
            List<ColetaCientifica> ccPorIdExpedicao = coletaRepo.ccPorIdExpedicao(2);

            for(ColetaCientifica cc : ccPorIdExpedicao) {
                System.out.println(cc.getDescricaoPonto() + "\nSetor:" + cc.getSetor().getDenominacao() + "\nPesquisador: " + cc.getPesquisador().getNome());
            }
            
            //Consulta as amostras pelo id da coleta cientifica 
            List<AmostraCientifica> acPorIdColetaCientifica = coletaRepo.acPorIdColetaCientifica(1);

            for (AmostraCientifica ac : acPorIdColetaCientifica) {
                System.out.println(ac.getCodAmostra() + " : " + ac.getColeta().getIdColeta());
            }

            //Equipamento
            EquipamentoRepository equipamentoRep = new EquipamentoRepository(em);

            //Busca equipamentos por situação e um intervalo de data
            List<Equipamento> equipamentosBuscarSituacaoPorData = equipamentoRep.equipamentosBuscarSituacaoPorData(situacaoOperacionalEquipamentoEnum.DISPONIVEL, LocalDate.of(2026, 10, 1), LocalDateTime.of(2026, 10, 10, 8, 0));

            for(Equipamento e : equipamentosBuscarSituacaoPorData) {
                System.out.println(e.getNome());
            }

//------------------- Consultas de Expedição-------------------------
            ExpedicaoRepository er = new ExpedicaoRepository(em);

            //Consulta Expedições por período e situação
            LocalDateTime inicio = LocalDateTime.of(2026, 9, 28, 0, 0, 0);
            LocalDateTime termino = LocalDateTime.of(2026, 9, 29, 23, 59, 59);
            situacaoExpedicaoEnum situacao = situacaoExpedicaoEnum.PLANEJADA;
            
            er.expedicoesporPeriodoSituacao(inicio, termino, situacao);

            //Consulta expedição selecionada, incluindo participantes e seus papéis
            er.expedicaoSelecionada(1);    
            
            //Busca PDF da autorização ambiental da expedição
            er.buscarPdfAutorizacaoExpedicao(1);

            //Busca mapa do plano de segurança da expedição
            er.buscarMapaPlanoExpedicao(1);

            //Busca arquivo completo do relatório da expedição
            er.buscarArqRelatorioExpedicao(1);

//------------------- Consultas de Cavernas-------------------------
            CavernaRepository cr = new CavernaRepository(em);

            //Busca cavernas que estão acessíveis atualmente
            cr.cavernasAcessiveis();

            //Busca cavernas com inspeção vencida
            cr.cavernaInspVencida(LocalDate.of(2026, 04, 01));

//------------------- Estudo ---------------------------------
            // List<Expedicao> qExpedicao = em.createQuery("Select e From Expedicao e", Expedicao.class).getResultList();

            // for(Expedicao e: qExpedicao){
            //     System.out.println(e.getCodExped());
            //     System.out.println(e.getTitulo());
            //     System.out.println(e.getSituacao());
            // }

            // List<Expedicao> qExpedicao = em.createQuery("Select e From Expedicao e Where inicio >= :dtInicio And termino <= :dtFim", Expedicao.class).setParameter("dtInicio",LocalDateTime.of(2026, 9, 28, 0, 0, 0)).setParameter("dtFim",LocalDateTime.of(2026, 9, 30, 0, 0, 0)).getResultList();
            // for(Expedicao e : qExpedicao){
            //     System.out.println(e.getCodExped());
            //     System.out.println(e.getTitulo());
            //     System.out.println(e.getSituacao());
            // }

            // List<Expedicao> expds = em.createQuery("Select e From Expedicao e join e.caverna", Expedicao.class).getResultList();
            // for(Expedicao e : expds){
            //     System.out.println(e.getCodExped());
            //     System.out.println(e.getTitulo());
            //     System.out.println(e.getCaverna().getNomeCaverna());
            // }

            // List<AmostraCientifica> as = em.createQuery("Select a From AmostraCientifica a Where a.coleta.id = :id", AmostraCientifica.class).setParameter("id", 1).getResultList();
            // for(AmostraCientifica a : as){
            //     System.out.println(a.getColeta().getIdColeta());
            //     System.out.println(a.getCodAmostra());
            //     System.out.println(a.getCategoria());
            // }

            // tx.begin();
            // Pessoa p = em.find(Pessoa.class, 2);
            // System.out.println(p.getNome());
            // p.setNome("Roberto Santos");
            // em.persist(p);
            // System.out.println(p.getNome());
            // tx.commit();


        } catch (Exception e) {
            System.out.println("Erro ao iniciar o JPA:");
            e.printStackTrace();

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}