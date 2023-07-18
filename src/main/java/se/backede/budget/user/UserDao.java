package se.backede.budget.user;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import se.backede.budget.generics.JfxDao;

/**
 *
 * @author joaki
 */
//@RequestScoped
@Slf4j
@EqualsAndHashCode
public class UserDao extends JfxDao<UserEntity> {

    public UserDao() {
        super(UserEntity.class);
    }

}
