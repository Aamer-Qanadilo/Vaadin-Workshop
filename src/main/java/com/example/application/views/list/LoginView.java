package com.example.application.views.list;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Route("login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {
    Navbar nav = new Navbar();
    H1 h1=new H1("Login Form");



    TextField email =new TextField("Email");

    TextField pass =new TextField("Password");

    Button loginButton = new Button("Login");






    public LoginView(){

        setClassName("register");

        loginButton.addClickListener(buttonClickEvent -> {
            String postEndpoint = "http://api.cup2022.ir/api/v1/user/login";
            String inputJson = "{ \"name\":\"tammy133\", \"salary\":\"5000\", \"age\":\"20\" }";
            JSONObject obj = new JSONObject();
            obj.put("email", email.getValue());
            obj.put("password", pass.getValue());

            var request = HttpRequest.newBuilder()
                    .uri(URI.create(postEndpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(obj.toJSONString()))
                    .build();
            var client = HttpClient.newHttpClient();
            try {
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            loginButton.getUI().ifPresent(UI -> UI.navigate(""));
        });

        add(h1,email,pass, loginButton);

    }

}