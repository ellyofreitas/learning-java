package br.com.ellyofreitas.learning.infra.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private final HttpServer httpServer;
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public Server() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        httpServer.createContext("/", new DefaultHandler());
        httpServer.setExecutor(null); // default executor
    }

    public void listen() {
        httpServer.start();
        logger.log(Level.INFO, "Server is running on port 8080");
    }

    public Server route(String path, HttpHandler handler) {
        httpServer.createContext(path, handler);
        return this;
    }

    static class DefaultHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            ServerResponse.notFound().build(t);
        }
    }
}
