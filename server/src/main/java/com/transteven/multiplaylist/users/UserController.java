package com.transteven.multiplaylist.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transteven.multiplaylist.users.UserService;
import com.transteven.multiplaylist.users.dto.LoginUserDTO;
import com.transteven.multiplaylist.users.dto.UserDTO;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/email-exists/{email}")
  public ResponseEntity<Boolean> emailExists(@PathVariable final String email) {
    final boolean exists = userService.emailExists(email);
    return ResponseEntity.ok(exists);
  }

  @GetMapping("/password/{user_id}/{password}")
  public ResponseEntity<Boolean> isValidPassword(
    @PathVariable final int user_id,
    @PathVariable final String password
  ) {
    final boolean isValidPassword = userService.isValidPassword(
      user_id,
      password
    );
    return ResponseEntity.ok(isValidPassword);
  }

  @PostMapping("/add")
  public ResponseEntity<Integer> addUser(@RequestBody final UserDTO user) {
    final int userID = userService.addUser(user);
    return new ResponseEntity<>(userID, HttpStatus.CREATED);
  }

  @GetMapping("/login_credentials/{email}/{password}")
  public ResponseEntity<LoginUserDTO> login(
    @PathVariable final String email,
    @PathVariable final String password
  ) {
    final LoginUserDTO user = userService.getUserByEmailAndPassword(
      email,
      password
    );
    return ResponseEntity.ok(user);
  }
}
