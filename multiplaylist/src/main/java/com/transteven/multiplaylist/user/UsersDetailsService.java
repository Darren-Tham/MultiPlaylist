package com.transteven.multiplaylist.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsService implements UserDetailsService {

  private final UserDAO userDAO;

  public UsersDetailsService(@Qualifier("jpa") UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    return userDAO
      .selectUserByEmail(username)
      .orElseThrow(() ->
        new UsernameNotFoundException("Username" + username + "not found")
      );
  }
}
