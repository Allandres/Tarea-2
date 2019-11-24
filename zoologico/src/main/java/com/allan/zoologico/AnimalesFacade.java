/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allan.zoologico;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author allan
 */
@Stateless
public class AnimalesFacade extends AbstractFacade<Animales> {

    @PersistenceContext(unitName = "com.allan_zoologico_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnimalesFacade() {
        super(Animales.class);
    }
    
}
