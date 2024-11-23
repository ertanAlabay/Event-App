package com.ertanAlabay.etkinlikApp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ertanAlabay.etkinlikApp.dto.UserDTO;
import com.ertanAlabay.etkinlikApp.mapper.UserMapper;
import com.ertanAlabay.etkinlikApp.model.User;
import com.ertanAlabay.etkinlikApp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Kullanıcı oluşturma
    @SuppressWarnings("unchecked")
	@PostMapping("/add")
    public ResponseEntity<UserDTO> createUser(@RequestBody Map<String, Object> requestData) {
        // Gelen tüm verileri al
        User user = new User();
        user.setUsername((String) requestData.get("username"));
        user.setPassword((String) requestData.get("password"));
        user.setEmail((String) requestData.get("email"));
        user.setName((String) requestData.get("name"));
        user.setSurname((String) requestData.get("surname"));
        user.setPhone((String) requestData.get("phone"));
        user.setProfilePicture((String) requestData.get("profilePicture"));
        user.setInterests(new HashSet<>((List<String>) requestData.get("interests")));

        // Kullanıcıyı kaydet ve DTO olarak döndür
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(UserMapper.toDTO(createdUser), HttpStatus.CREATED);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(UserMapper.toDTO(user), HttpStatus.OK);
    }
    // Kullanıcı güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, UserMapper.toEntity(userDTO));
        return new ResponseEntity<>(UserMapper.toDTO(updatedUser), HttpStatus.OK);
    }

    // Kullanıcı silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Tüm kullanıcıları listeleme
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                          .stream()
                          .map(UserMapper::toDTO)
                          .collect(Collectors.toList());
    }
}