package com.garvk.CrudProj.services;

import com.garvk.CrudProj.models.User;
import com.garvk.CrudProj.models.UserPrincipal;
import com.garvk.CrudProj.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AbstractUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User lUser = userRepo.findByUsername(username);

        if(null == lUser){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(lUser);
    }
}
