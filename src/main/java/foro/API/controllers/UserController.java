package foro.API.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import foro.API.dto.PasswordChangeRequest;
import foro.API.models.User;
import foro.API.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllUsers() throws Exception {
        try {
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userID}")
    public ResponseEntity<?> getUserByID(@PathVariable Long userID) throws Exception {
        try {
            return ResponseEntity.ok(userService.getUserByID(userID));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(userService.findByEmail(email));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User userToCreate) throws Exception {
        try {
            return ResponseEntity.ok(userService.createUser(userToCreate));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/uploadProfilePicture")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        try {
            String fileName = userService.saveProfilePicture(id, file);
            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User userToUpdate) throws Exception {
        try {
            return ResponseEntity.ok(userService.updateUser(userToUpdate));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/updatePassword")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody PasswordChangeRequest passwordChangeRequest) {
        try {
            String newPassword = passwordChangeRequest.getPassword();
            return ResponseEntity.ok(userService.updatePassword(id, newPassword));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }   
}
