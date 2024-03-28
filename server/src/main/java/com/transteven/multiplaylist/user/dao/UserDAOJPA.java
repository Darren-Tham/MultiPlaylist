package com.transteven.multiplaylist.user.dao;

import com.transteven.multiplaylist.user.User;
import com.transteven.multiplaylist.user.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOJPA implements UserDAO {

  private final UserRepository userRepository;

  public UserDAOJPA(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean emailExists(final String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public Optional<User> getUserByEmail(final String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public void createUser(final User user) {
    userRepository.save(user);
  }

  @Override
  public void updateUser(final User user) {
    userRepository.save(user);
  }
}
