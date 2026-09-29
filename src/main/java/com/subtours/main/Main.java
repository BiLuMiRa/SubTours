package com.subtours.main;

import jakarta.transaction.UserTransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.subtours.controller.GenericController;
import com.subtours.enums.*;
import com.subtours.infra.JpaUtil;
import com.subtours.model.*;
import com.subtours.repository.ColetaCientificaRepository;
import com.subtours.repository.EquipamentoRepository;
import com.subtours.repository.ExpedicaoRepository;


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

            // GenericController<Pessoa> pessoaController = new GenericController<>(Pessoa.class);
            // Pessoa p = pessoaController.buscarReferencia(em, 1);
            // System.out.println(p.getNome());

            // LocalDate dataInicio = LocalDate.of(2026, 10, 1);
            // LocalDateTime dataFim = LocalDateTime.of(2026, 10, 10, 8, 0);
            // List <Equipamento> equipamentos = em.createNamedQuery("Equipamentos.buscaSituacaoPorData", Equipamento.class)
            //     .setParameter("situacao", situacaoOperacionalEquipamentoEnum.disponivel)
            //     .setParameter("dataInicio", dataInicio)
            //     .setParameter("dataFim", dataFim)
            //     .getResultList();
            
            // for (Equipamento e : equipamentos){
            //     System.out.println(e.getNome());
            // }
            //  yste   Sm.out.println(e.getNome());
            // }

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