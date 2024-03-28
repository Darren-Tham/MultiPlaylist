package com.transteven.multiplaylist.user;

import com.google.api.services.youtube.model.Entity;
import com.transteven.multiplaylist.user.UserRepository;
import com.transteven.multiplaylist.user.dao.UserDAO;
import com.transteven.multiplaylist.user.dto.LoginUserDTO;
import com.transteven.multiplaylist.user.exceptions.LoginCredentialsInvalidException;
import com.transteven.multiplaylist.user.exceptions.UserNotFoundException;
import jakarta.persistence.EntityManager;
import org.checkerframework.checker.units.qual.A;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserDAO userDAO;
  private final EntityManager entityManager;

  @Autowired
  public UserService(final UserDAO userDAO, final EntityManager entityManager) {
    this.userDAO = userDAO;
    this.entityManager = entityManager;
  }

  public boolean emailExists(final String email) {
    return userDAO.emailExists(email);
  }

  public int getUserIdByEmail(final String email) {
    return userDAO
      .getUserByEmail(email)
      .orElseThrow(() ->
        new UserNotFoundException(
          String.format("User with email `%s` does not exist.", email)
        )
      )
      .getId();
  }

  public boolean isValidPassword(final int userId, final String password) {
    final User user = entityManager.find(User.class, userId);
    return BCrypt.checkpw(password, user.getEncryptedPassword());
  }

  public LoginUserDTO getUserByEmailAndPassword(
    final String email,
    final String password
  ) {
    final User user = userDAO
      .getUserByEmail(email)
      .orElseThrow(() ->
        new UserNotFoundException(
          String.format("User with email `%s` does not exist.", email)
        )
      );
    if (BCrypt.checkpw(password, user.getEncryptedPassword())) {
      return new LoginUserDTO(user.getId(), user.getEmail());
    } else {
      throw new LoginCredentialsInvalidException(
        "Email or password credential is incorrect."
      );
    }
  }
}
