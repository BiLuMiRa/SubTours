package com.subtours.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.subtours.enums.situacaoExpedicaoEnum;
import com.subtours.model.AutorizacaoAmbiental;
import com.subtours.model.Expedicao;
import com.subtours.model.Participacao;
import com.subtours.model.PlanoSeguranca;
import com.subtours.model.Relatorio;

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
        System.out.println("ID: " + expedicao.getIdExped());
        System.out.println("Cod Expedição: " + expedicao.getCodExped());
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
        System.out.println();
    }

//---------------- Consulta Expedições por período e situação
    public void expedicoesporPeriodoSituacao(LocalDateTime inicio, LocalDateTime termino, situacaoExpedicaoEnum situacao){
        TypedQuery<Object[]> queryExpedicaoPeriodoSituacao =
            em.createQuery("Select ex.codExped, ex.titulo, ex.caverna.idCaverna, ex.caverna.nomeCaverna, ex.inicio, ex.termino, ex.situacao From Expedicao ex Where ex.inicio >= :inicio And ex.termino <= :termino And ex.situacao = :situacao Order by ex.inicio", Object[].class).setParameter("inicio", inicio).setParameter("termino", termino).setParameter("situacao", situacao);

        List<Object[]> resultados = queryExpedicaoPeriodoSituacao.getResultList();
        System.out.println("=== DETALHES DAS EXPEDIÇÕES ===");
        System.out.println("Quantidade de resultados: " + resultados.size());
        for (Object[] resultado : resultados) {
            System.out.println("COD: " + resultado[0]);
            System.out.println("Título: " + resultado[1]);
            System.out.println("ID Caverna: " + resultado[2]);
            System.out.println("Nome Caverna: " + resultado[3]);
            System.out.println("Início: " + resultado[4]);
            System.out.println("Término: " + resultado[5]);
            System.out.println("Situação: " + resultado[6]);
            System.out.println();
        }
        System.out.println();
    }

//---------------- Busca PDF da autorização ambiental da expedição
    public void buscarPdfAutorizacaoExpedicao(Integer idExpedicao){
        TypedQuery<Object> queryPdfAutorizacao = em.createQuery("Select e.autorizAmbiental.pdfAssinado From Expedicao e Where e.idExped = :id", Object.class).setParameter("id", idExpedicao);
        Object resultado = queryPdfAutorizacao.getSingleResult();
        System.out.println("=== PDF AUTORIZAÇÃO AMBIENTAL ===");
        System.out.println("PDF: " + resultado);
        System.out.println();
    }

//---------------- Busca mapa do plano de segurança da expedição
    public void buscarMapaPlanoExpedicao(Integer idExpedicao){
        TypedQuery<PlanoSeguranca> queryMapaPlano = em.createQuery("Select e.planoSeguranca.mapa From Expedicao e Where e.idExped = :id", PlanoSeguranca.class).setParameter("id", idExpedicao);
        PlanoSeguranca resultado = queryMapaPlano.getSingleResult();
        System.out.println("=== MAPA PLANO DE SEGURANÇA ===");
        System.out.println("Mapa: " + resultado);
        System.out.println();
    }

//---------------- Busca arquivo completo do relatório da expedição
    public void buscarArqRelatorioExpedicao(Integer idExpedicao){
        TypedQuery<byte[]> queryArqRelatorio = em.createQuery("Select e.relatorio.arqCompleto From Expedicao e Where e.idExped = :id", byte[].class).setParameter("id", idExpedicao);
        byte[] resultado = queryArqRelatorio.getSingleResult();
        System.out.println("=== ARQUIVO COMPLETO DO RELATÓRIO ===");
        System.out.println("Arquivo: " + (resultado != null));
        System.out.println();
    }
}