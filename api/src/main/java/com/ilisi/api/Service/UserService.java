package com.ilisi.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilisi.api.Bo.User;
import com.ilisi.api.Repository.UserRepository;
import com.ilisi.api.Dto.UserDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("null")
    public UserDTO create(UserDTO userDTO) {
        return this.fromUser(userRepository.save(this.toUser(userDTO)));
    }

    public List<UserDTO> retrieve() {
        return userRepository.findAll().stream().map(u->fromUser(u)).collect(Collectors.toList());
        
    }

    public UserDTO fromUser(User user) {
        return UserDTO.builder()
            .id(user.getId())
            .nom(user.getNom())
            .age(user.getAge())
            .build();
        //insted of this code we are using the builder
        // UserDTO userDTO = new UserDTO();
        // userDTO.setId(user.getId());
        // userDTO.setNom(user.getNom());
        // userDTO.setAge(user.getAge());
        
        // return userDTO;
    }

    public User toUser(UserDTO userDTO) {
        return User.builder()
            .id(userDTO.getId())
            .nom(userDTO.getNom())
            .age(userDTO.getAge())
            .build();
    }
    
    @SuppressWarnings("null")
    public UserDTO update(UserDTO userDTO,int id) {
        
        Optional<User>user=userRepository.findById(id);
        if(user.isPresent()) {
            UserDTO userUpdated=this.fromUser(user.get());
            userUpdated.setNom(userDTO.getNom());
            userUpdated.setAge(userDTO.getAge());
            this.fromUser(userRepository.save(this.toUser(userUpdated)));
            return userUpdated;
        }else
        {
            throw new RuntimeException("User not found");
        }
    }

    public UserDTO getUser(int id) {
        Optional<User>user=userRepository.findById(id);
        if(user.isPresent()) {
            return this.fromUser(user.get());
        }else
        {
            throw new RuntimeException("User not found");
        }
    }

    @SuppressWarnings("null")
    public void deleteUser(int id) {
        Optional<User>user=userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.delete(user.get());
        }else
        {
            throw new RuntimeException("User not found");
        }
    }
}
