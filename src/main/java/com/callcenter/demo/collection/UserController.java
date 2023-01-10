package com.callcenter.demo.collection;

import com.callcenter.demo.model.CommonResponse;
import com.callcenter.demo.model.User;
import com.callcenter.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CommonResponse> register(
            @Validated(User.New.class) @RequestBody User user
    ) {
        try {
            log.info(user.toString());
            User data = userService.createUser(user);
            return ResponseEntity.ok(CommonResponse.builder().success(true).data(data).build());

        } catch (Exception e) {
            log.error(String.valueOf(e));
            return ResponseEntity.ok(CommonResponse.builder().success(false).message("Error in creating user.").build());

        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<CommonResponse> updateUser(
            @PathVariable("id") Integer userId, @RequestBody User user
    ) {
        try {
            User data = userService.updateUser(userId, user);
            return ResponseEntity.ok(CommonResponse.builder().success(true).data(data).build());

        } catch (Exception e) {
            return ResponseEntity.ok(CommonResponse.builder().success(false).message("Error in updating user.").build());

        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public Optional<User> findUserByUserId(@PathVariable("id") Integer userId) {
        return userService.findUserByUserId(userId);
    }

    @GetMapping(path = "/getUserByEmail/{email}")
    public Optional<User> findUserByEmail(@PathVariable("email") String email) {
        return userService.findUserByEmail(email);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAccountById(@PathVariable("id") Integer userId) {
        userService.deleteUser(userId);
    }

}
