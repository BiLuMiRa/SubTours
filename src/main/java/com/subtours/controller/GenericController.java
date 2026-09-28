package com.subtours.controller;

import jakarta.persistence.EntityManager;

public class GenericController<T> {
    
    private final Class<T> classe;

    public GenericController(Class<T> classe) {
        this.classe = classe;
    }

    public void criar(EntityManager em, T entidade){
        em.persist(entidade);
    }

    public T buscaPorId(EntityManager em, int id){
        return em.find(classe, id);
    }

    public T buscarReferencia(EntityManager em, int id){
        return em.getReference(classe, id);
    }

    public T atualizar(EntityManager em, T entidade){
        return em.merge(entidade);
    }

    public void desvincular(EntityManager em, T entidade){
        em.detach(entidade);
    }

    public void apagar(EntityManager em, int id){
        T entidade = em.find(classe, id);

        if(entidade != null){
            em.remove(entidade);
        }
    } 
}
