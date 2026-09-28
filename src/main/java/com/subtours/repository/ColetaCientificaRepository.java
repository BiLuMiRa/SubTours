package com.subtours.repository;

import java.util.List;


import com.subtours.model.AmostraCientifica;
import com.subtours.model.ColetaCientifica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ColetaCientificaRepository {
    
    private EntityManager em;
    
    public ColetaCientificaRepository(EntityManager em){
        this.em = em;
    }

    //Lista as coletas de uam expedição com setor e pesquisador reponsável
    public List<ColetaCientifica> ccPorIdExpedicao(Integer id) {
        TypedQuery<ColetaCientifica> typedQueryCc = 
            em.createQuery("Select cc From ColetaCientifica cc Join Fetch cc.setor Join Fetch cc.pesquisador Where cc.expedicao.id = :id", ColetaCientifica.class).setParameter("id", id);

        return typedQueryCc.getResultList();
    }

    //Consulta as amostras pelo id da coleta cientifica 
    public List<AmostraCientifica> acPorIdColetaCientifica(Integer id){
        TypedQuery<AmostraCientifica> typedQueryAc = em.createQuery("Select ac From AmostraCientifica ac Where ac.coleta.id = :id", AmostraCientifica.class);

        return typedQueryAc.getResultList();
    }

}
