package foro.API.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foro.API.models.User;
import foro.API.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    @Autowired
    private final UserRepository userRepository;

    public List<User> getAll() throws Exception {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error fetching data: "+e.getMessage());
        }
    }
    public User getUserByID(Long ID) throws Exception {
        return userRepository.findById(ID).orElseThrow(
            () -> new Exception("This user doesn't exist!") 
        );
    }
    public void deleteUserByID(Long ID) throws Exception {
        if (!userRepository.existsById(ID)) {
            throw new Exception("This user doesn't exist!");
        }
        userRepository.deleteById(ID);
    }
    public User createUser(User userToCreate) throws Exception {
        return userRepository.save(userToCreate);
    }
    public User updateUser(User userDetails) throws Exception {
        User userToUpdate = userRepository.findById(userDetails.getId())
        .orElseThrow(() -> new Exception("This user doesn't exist!"));

        userToUpdate.setUsername(userDetails.getUsername());
        userToUpdate.setName(userDetails.getName());
        userToUpdate.setLastname(userDetails.getLastname());
        userToUpdate.setEmail(userDetails.getEmail());
        userToUpdate.setPassword(userDetails.getPassword());

        return userRepository.save(userToUpdate);
    }
    public User updatePassword(Long id, String password) throws Exception {
        User userToUpdate = userRepository.findById(id)
        .orElseThrow(() -> new Exception("This user doesn't exist!"));

        userToUpdate.setPassword(password);

        return userRepository.save(userToUpdate);
    }
    public User login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
