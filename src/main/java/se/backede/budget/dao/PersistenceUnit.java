/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Joakim Backede
 */
public class PersistenceUnit {

    @PersistenceContext
    private static EntityManager em;

    protected static EntityManagerFactory emf;

    public static EntityManager getEntityManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("PU");
            em = emf.createEntityManager();
        }
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }

    public static EntityManager getEntityManager(String name) {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory(name);
            em = emf.createEntityManager();
        }
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }

}
