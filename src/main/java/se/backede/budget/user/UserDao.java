package se.backede.budget.user;

import jakarta.persistence.EntityManager;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import se.backede.budget.dao.PersistenceUnit;
import se.backede.generics.persistence.GenericDao;

/**
 *
 * @author joaki
 */
//@RequestScoped
@Slf4j
@EqualsAndHashCode
public class UserDao extends GenericDao<UserEntity> {

    private static UserDao instance;

    @Override
    public EntityManager getEntityManager() {
        return PersistenceUnit.getEntityManager();
    }

    @Override
    public EntityManager getEntityManager(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public Optional<UserEntity> persistUser(UserEntity entity) {
        return executeTransaction(() -> persist(entity));
    }

    public Optional<Boolean> deleteUser(UserEntity entity) {
        return executeTransactionBoolean(() -> delete(entity.getId()));
    }

}
