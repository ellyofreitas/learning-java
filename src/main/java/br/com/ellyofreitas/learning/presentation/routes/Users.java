package br.com.ellyofreitas.learning.presentation.routes;

import java.io.IOException;
import com.sun.net.httpserver.HttpHandler;
import br.com.ellyofreitas.learning.infra.http.ServerResponse;
import br.com.ellyofreitas.learning.presentation.controllers.UserController;
import br.com.ellyofreitas.learning.presentation.http.HttpRequest;
import br.com.ellyofreitas.learning.presentation.http.HttpResponse;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Users implements HttpHandler {
    private Map<String, Method> methodMap = new HashMap<>();

    public Users() throws NoSuchMethodException {
        methodMap.put("POST", UserController.class.getMethod("create", HttpRequest.class));
        methodMap.put("GET", UserController.class.getMethod("list", HttpRequest.class));
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        String requestMethod = t.getRequestMethod();
        Method method = methodMap.get(requestMethod);
        if (method == null) {
            ServerResponse.notFound().build(t);
            return;
        }
        try {
            HttpRequest httpRequest = new HttpRequest(t.getRequestBody());
            HttpResponse httpResponse = (HttpResponse) method.invoke(new UserController(), httpRequest);
            String response = httpResponse.getBody().toString();
            t.sendResponseHeaders(httpResponse.getStatusCode(), response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            ServerResponse.serverError().build(t);
        }
    }
}
