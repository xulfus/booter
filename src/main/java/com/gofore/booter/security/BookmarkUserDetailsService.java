package com.gofore.booter.security;


import com.gofore.booter.model.Account;
import com.gofore.booter.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookmarkUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Account> a = accountRepository.findByUsername(name);
        System.out.println("retrieved user account " + a);
        if (a.isPresent())
            return a.get();
        else
            throw new UsernameNotFoundException(name);
    }
}
