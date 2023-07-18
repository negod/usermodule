/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget.generics;

import jakarta.persistence.EntityManager;
import java.util.Optional;
import se.backede.budget.dao.PersistenceUnit;
import se.backede.generics.persistence.GenericDao;
import se.backede.generics.persistence.entity.GenericEntity;

/**
 *
 * @author joaki
 */
public class JfxDao<T extends GenericEntity> extends GenericDao<T> {

    private static JfxDao instance;

    public JfxDao(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public EntityManager getEntityManager() {
        return PersistenceUnit.getEntityManager();
    }

    @Override
    public EntityManager getEntityManager(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static JfxDao getInstance(Class clazz) {
        if (instance == null) {
            instance = new JfxDao(clazz);
        }
        return instance;
    }

    public Optional<T> persistEntity(T entity) {
        return executeTransaction(() -> persist(entity));
    }

    public Optional<Boolean> deleteEntity(T entity) {
        return executeTransactionBoolean(() -> delete(entity.getId()));
    }

}
