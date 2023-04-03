package br.com.ellyofreitas.learning.infra.memory;

import java.util.ArrayList;
import java.util.List;

import br.com.ellyofreitas.learning.domain.entities.User;
import br.com.ellyofreitas.learning.domain.repositories.UserRepository;

public class MemoryUserRepository implements UserRepository {
    @Override
    public void save(User user) {
      // TODO document why this method is empty
    }

    @Override
    public List<User> getAllUsers() {
      return new ArrayList<>();
    }
}
