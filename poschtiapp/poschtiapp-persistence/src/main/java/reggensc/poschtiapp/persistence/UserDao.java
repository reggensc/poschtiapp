package reggensc.poschtiapp.persistence;

import org.springframework.stereotype.Repository;

import reggensc.poschtiapp.domain.User;

@Repository
public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }

}
