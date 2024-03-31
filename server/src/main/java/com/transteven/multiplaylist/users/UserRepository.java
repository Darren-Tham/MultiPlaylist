package com.transteven.multiplaylist.users;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  @Query(
    "SELECT CASE WHEN COUNT(c) = 1 THEN true ELSE false END FROM User c WHERE c.email = ?1"
  )
  boolean existsByEmail(final String email);

  Optional<User> findByEmail(final String email);
}
