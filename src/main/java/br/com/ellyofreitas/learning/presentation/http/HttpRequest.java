package br.com.ellyofreitas.learning.presentation.http;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class HttpRequest {
  private JsonElement body;

  public HttpRequest(InputStream bodyStream) throws IOException {
    byte[] bytes = bodyStream.readAllBytes();
    String requestBodyString = new String(bytes, StandardCharsets.UTF_8);
    Gson gson = new Gson();
    this.body = gson.fromJson(requestBodyString, JsonElement.class);
  }

  public JsonElement getBody() {
    return body;
  }

  public <T> T getBodyAs(Class<T> clazz) {
    Gson gson = new Gson();
    return gson.fromJson(body, clazz);
  }
}
