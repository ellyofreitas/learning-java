package br.com.ellyofreitas.learning.presentation.controllers;

import java.util.List;

import br.com.ellyofreitas.learning.app.usecases.CreateUser;
import br.com.ellyofreitas.learning.app.usecases.ListUsers;
import br.com.ellyofreitas.learning.app.usecases.CreateUser.CreateUserInput;
import br.com.ellyofreitas.learning.domain.entities.User;
import br.com.ellyofreitas.learning.domain.errors.ValidationError;
import br.com.ellyofreitas.learning.infra.mysql.MySQLUserRepository;
import br.com.ellyofreitas.learning.presentation.http.HttpRequest;
import br.com.ellyofreitas.learning.presentation.http.HttpResponse;

public class UserController {
    public HttpResponse list(HttpRequest httpRequest) {
        MySQLUserRepository userRepository = new MySQLUserRepository();
        ListUsers listUers = new ListUsers(userRepository);
        List<User> users = listUers.perform();
        return HttpResponse.created(users);

    }

    public HttpResponse create(HttpRequest httpRequest) {
        try {
            MySQLUserRepository userRepository = new MySQLUserRepository();
            CreateUser createUser = new CreateUser(userRepository);
            CreateUserInput input = httpRequest.getBodyAs(CreateUserInput.class);
            User user = createUser.perform(input);
            return HttpResponse.created(user);
        } catch (ValidationError e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }
}
