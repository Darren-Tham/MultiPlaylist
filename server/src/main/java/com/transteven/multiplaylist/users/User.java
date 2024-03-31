package com.transteven.multiplaylist.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Table;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(insertable = false, updatable = false)
  protected int id;

  @Column(nullable = false)
  protected String encryptedPassword;

  @Column(nullable = false, updatable = false, unique = true)
  private String email;

  protected User(final String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
  }

  public User(final String email, final String encryptedPassword) {
    this.email = email;
    this.encryptedPassword = encryptedPassword;
  }
}
