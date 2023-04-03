package br.com.ellyofreitas.learning.infra.http;

import java.io.IOException;
import java.io.OutputStream;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;

public class ServerResponse {
    private int statusCode;
    private JsonElement body;

    public ServerResponse(Builder builder) {
        this.statusCode = builder.statusCode;
        JsonObject response = new JsonObject();
        response.addProperty("message", builder.message);
        this.body = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public JsonElement getBody() {
        return body;
    }

    public static Builder badRequest() {
        return new Builder(400, "bad request");
    }

    public static Builder badRequest(String message) {
        return new Builder(400, message);
    }

    public static Builder notFound() {
        return new Builder(404, "not found");
    }

    public static Builder notFound(String message) {
        return new Builder(404, message);
    }

    public static Builder serverError() {
        return new Builder(500, "server error");
    }

    public static Builder serverError(String message) {
        return new Builder(500, message);
    }

    public static class Builder {
        private int statusCode;
        private String message;

        public Builder(int statusCode, String message) {
            this.statusCode = statusCode;
            this.message = message;
        }

        public void build(HttpExchange t) throws IOException {
            ServerResponse serverResponse = new ServerResponse(this);
            String response = serverResponse.getBody().toString();
            t.sendResponseHeaders(serverResponse.getStatusCode(), response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
