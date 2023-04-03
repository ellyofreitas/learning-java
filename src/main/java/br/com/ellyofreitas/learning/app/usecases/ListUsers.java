package br.com.ellyofreitas.learning.app.usecases;

import java.util.List;

import br.com.ellyofreitas.learning.domain.entities.User;
import br.com.ellyofreitas.learning.domain.repositories.UserRepository;

public class ListUsers {
    public final UserRepository userRepository;
    
    public ListUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
     }

     public List<User> perform() {
        return userRepository.getAllUsers();
     }
}
