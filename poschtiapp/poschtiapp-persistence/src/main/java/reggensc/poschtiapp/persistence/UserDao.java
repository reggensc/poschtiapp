package reggensc.poschtiapp.persistence;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import reggensc.poschtiapp.domain.User;

@Repository
public class UserDao extends AbstractDao<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    public UserDao() {
        super(User.class);
    }

    public User getByEmail(String email) {
        LOGGER.trace("Trying to load user with email: {}", email);

        Query query = em.createNamedQuery("findByEmail");
        query.setParameter("email", email);

        User user = (User) query.getSingleResult();

        LOGGER.trace("Successfully loaded {} user with email: {}", user, email);
        return user;
    }

    public String getPassword(String email) {
        LOGGER.trace("Trying to load password for user with email: {}", email);

        Query query = em.createNamedQuery("findPassword");
        query.setParameter(1, email);

        String password = (String) query.getSingleResult();

        LOGGER.trace("Successfully loaded password for user with email: {}", email);
        return password;
    }

}
