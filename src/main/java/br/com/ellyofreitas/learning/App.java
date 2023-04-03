package br.com.ellyofreitas.learning;

import java.io.IOException;
import br.com.ellyofreitas.learning.infra.http.Server;
import br.com.ellyofreitas.learning.infra.mysql.MySQLConnection;
import br.com.ellyofreitas.learning.presentation.routes.Users;

public class App {
    public static void main(String[] args) throws IOException, NoSuchMethodException {
        MySQLConnection.addShutdownHook();
        new Server()
                .route("/users", new Users())
                .listen();
    }
}
