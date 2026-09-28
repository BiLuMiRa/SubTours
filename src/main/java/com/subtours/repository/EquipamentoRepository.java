package com.subtours.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.subtours.enums.situacaoOperacionalEquipamentoEnum;
import com.subtours.model.Equipamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EquipamentoRepository {
    
    private  EntityManager em;

    public EquipamentoRepository(EntityManager em) {
        this.em = em;
    }

    //Busca equipamentos por situação e um intervalo de data
    public List<Equipamento> equipamentosBuscarSituacaoPorData(situacaoOperacionalEquipamentoEnum situacao, LocalDate dataInicio, LocalDateTime dataFim){
        TypedQuery<Equipamento> typedQueryEquipamentos = em.createQuery(
            "<![CDATA[ Select e From Equipamento e Where e.situacaoOperacional like :situacao AND NOT EXISTS (Select ue From UtilizacaoEquipamento ue where ue.equipamento = e AND ue.dataHoraRetirada < :dataFim AND (ue.dataDevolucao IS NULL AND ue.previsaoDevolucao > :dataInicio OR ue.dataDevolucao > :dataInicio))]]>", Equipamento.class
        );

        return typedQueryEquipamentos.getResultList();
    }
}
