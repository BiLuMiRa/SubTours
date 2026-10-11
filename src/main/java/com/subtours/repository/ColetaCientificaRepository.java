package com.subtours.repository;

import java.util.List;


import com.subtours.model.AmostraCientifica;
import com.subtours.model.Caverna;
import com.subtours.model.ColetaCientifica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ColetaCientificaRepository {
    
    private EntityManager em;
    
    public ColetaCientificaRepository(EntityManager em){
        this.em = em;
    }

    //Lista as coletas de uma expedição com setor e pesquisador reponsável
    public void ccPorIdExpedicao(Integer id) {
        List<ColetaCientifica> QueryCc = 
            em.createQuery("Select cc From ColetaCientifica cc Join Fetch cc.setor Join Fetch cc.pesquisador Where cc.expedicao.id = :id", ColetaCientifica.class).setParameter("id", id).getResultList();
        
        System.out.println("=== Coletas com setor e pesquisador responsável ===");
        for(ColetaCientifica cc : QueryCc) {
            System.out.println("Descrição do ponto: " + cc.getDescricaoPonto() + "\nSetor: " + cc.getSetor().getDenominacao() + "\nPesquisador: " + cc.getPesquisador().getNome());
        }
    }

    //Consulta as amostras pelo id da coleta cientifica 
    public void acPorIdColetaCientifica(Integer id){
        List<AmostraCientifica> QueryAc = em.createQuery("Select ac From AmostraCientifica ac Where ac.coleta.id = :id", AmostraCientifica.class).setParameter("id", id).getResultList();

        System.out.println("=== Amostras pelo id da coleta cientifica ===");
        for(AmostraCientifica ac : QueryAc) {
            System.out.println("Id da coleta cientifica " + ac.getColeta().getIdColeta() + "\nCod da amostra: " + ac.getCodAmostra() + "\nCategoria da amostra: " + ac.getCategoria() + "\n");
        }
    }

}
