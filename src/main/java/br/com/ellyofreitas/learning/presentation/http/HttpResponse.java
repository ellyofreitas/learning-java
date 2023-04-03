package br.com.ellyofreitas.learning.presentation.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class HttpResponse {
  private int statusCode;
  private JsonElement body;

  public HttpResponse(int statusCode, Object responseBody) {
    this.statusCode = statusCode;
    Gson gson = new Gson();
    this.body = gson.toJsonTree(responseBody);
  }

  public int getStatusCode() {
    return statusCode;
  }

  public JsonElement getBody() {
    return body;
  }

  public static HttpResponse created(Object data) {
    return new HttpResponse(201, data);
  }

  public static HttpResponse badRequest() {
    return new HttpResponse(400, new HttpResponseMessage("bad request"));
  }

  public static HttpResponse badRequest(String message) {
    return new HttpResponse(400, new HttpResponseMessage(message));
  }

  public static class HttpResponseMessage {
      private final String message;

      public HttpResponseMessage(String message) {
        this.message = message;
      }
  }
}
