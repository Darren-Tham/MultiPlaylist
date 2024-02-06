package com.transteven.multiplaylist.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
  boolean existsUserWithEmail(String email);
}