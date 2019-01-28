package com.accounts.app.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accounts.app.repositories.IUserRepository;

/**
 * Service to manage the UserDetails required by for the 
 * security implementation
 * 
 * @author adanp
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository repo;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	return repo
    				.findByUsername(username)
    				.map(u -> new org.springframework.security.core.userdetails.User(
    						u.getUsername(),
    		                u.getPassword(),
    		                u.isEnabled(),
    		                u.isEnabled(),
    		                u.isEnabled(),
    		                u.isEnabled(),
    		                AuthorityUtils.createAuthorityList(
    		                        u.getRoles()
    		                                .stream()
    		                                .map(r -> "ROLE_" + r.getName().toUpperCase())
    		                                .collect(Collectors.toList())
    		                                .toArray(new String[]{}))))
    		                .orElseThrow(() -> new UsernameNotFoundException("No user with "
    		                        + "the name " + username + "was found in the database"));
    	
    }
}