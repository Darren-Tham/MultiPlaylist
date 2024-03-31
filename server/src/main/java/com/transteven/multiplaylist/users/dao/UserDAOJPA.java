package com.transteven.multiplaylist.users.dao;

import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.transteven.multiplaylist.users.User;
import com.transteven.multiplaylist.users.UserRepository;

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
  public int addUser(final User user) {
    return userRepository.save(user).getId();
  }

  @Override
  public void updateUser(final User user) {
    userRepository.save(user);
  }
}
