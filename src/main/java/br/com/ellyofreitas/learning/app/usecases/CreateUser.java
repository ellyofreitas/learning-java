package br.com.ellyofreitas.learning.app.usecases;

import java.util.UUID;

import br.com.ellyofreitas.learning.domain.entities.User;
import br.com.ellyofreitas.learning.domain.repositories.UserRepository;

public class CreateUser {
   public final UserRepository userRepository;

   public CreateUser(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public class CreateUserInput {
      String name;
      String email;
   
      public CreateUserInput(String name, String email) {
         this.name = name;
         this.email = email;
     }
   }

   public User perform(CreateUserInput input) {
      User user = new User(UUID.randomUUID().toString(), input.name, input.email);
      userRepository.save(user);
      return user;
   }
}
