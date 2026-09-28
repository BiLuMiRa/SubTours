package com.subtours.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.subtours.enums.situacaoExpedicaoEnum;
import com.subtours.model.Expedicao;
import com.subtours.model.Participacao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ExpedicaoRepository {
    private EntityManager em;

    public ExpedicaoRepository (EntityManager em) {
        this.em = em;
    }

// --------------- Consulta expedição selecionada, incluindo participantes e seus papéis
    public void expedicaoSelecionada(Integer id){
        TypedQuery<Expedicao> queryExpedicaoParticipantes = em.createQuery(
        "Select ex From Expedicao ex Left Join Fetch ex.participacoes p Left Join Fetch p.pessoa Where ex.id = :id", Expedicao.class).setParameter("id", id);

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
    }

//---------------- Consulta Expedições por período e situação
    public void expedicoesporPeriodoSituacao(LocalDateTime inicio, LocalDateTime termino, situacaoExpedicaoEnum situacao){
        TypedQuery<Object[]> queryExpedicaoPeriodoSituacao =
            em.createNamedQuery("Select ex.id, ex.titulo, ex.caverna.numCaverna, ex.caverna.nomeCaverna, ex.inicio, ex.termino, ex.situacao From Expedicao ex Where ex.inicio >= :inicio And ex.termino &lt;= :termino And ex.situacao = :situacao Order by ex.inicio", Object[].class).setParameter("inicio", inicio).setParameter("termino", termino).setParameter("situacao", situacao);

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
    }
        
}
