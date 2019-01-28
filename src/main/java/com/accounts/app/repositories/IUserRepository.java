package com.accounts.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounts.app.entities.User;

/**
 * The interface for the User Repository
 * @author adanp
 *
 */

public interface IUserRepository extends JpaRepository<User, Long> {
	
	User findOneByUsername(String username);

	Optional<User> findByUsername(String username);

}