package br.com.ellyofreitas.learning.domain.repositories;

import java.util.List;

import br.com.ellyofreitas.learning.domain.entities.User;

public interface UserRepository {
    public void save(User user);
    public List<User> getAllUsers();
}
