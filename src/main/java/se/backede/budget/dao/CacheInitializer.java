/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget.dao;

import jakarta.persistence.EntityManager;
import se.backede.generics.persistence.entity.EntityRegistry;

/**
 *
 * @author Joakim Backede
 */
public class CacheInitializer extends EntityRegistry {

    @Override
    public EntityManager getEntityManager() {
        return PersistenceUnit.getEntityManager();
    }

    public CacheInitializer() {
        super.registerEnties();
    }

}
