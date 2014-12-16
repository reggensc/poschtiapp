package reggensc.poschtiapp.web.springsecurity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import reggensc.poschtiapp.domain.User;
import reggensc.poschtiapp.persistence.UserDao;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) {
        User user = userDao.getByEmail(username);
        String password = userDao.getPassword(username);

        return new UserDetails(user, password, getDefaultAuthorities());
    }

    private Collection<? extends GrantedAuthority> getDefaultAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority defaultAuthority = new SimpleGrantedAuthority("USER");
        authorities.add(defaultAuthority);
        return authorities;
    }

}
