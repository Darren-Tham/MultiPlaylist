package com.transteven.multiplaylist.users.dao;

import java.util.Optional;

import com.transteven.multiplaylist.users.User;

public interface UserDAO {
  boolean emailExists(String email);
  Optional<User> getUserByEmail(String email);
  int addUser(User user);
  void updateUser(User user);
}
