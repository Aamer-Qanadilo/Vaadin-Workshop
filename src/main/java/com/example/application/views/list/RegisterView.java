package com.example.application.views.list;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
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

@Route("register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {
    H1 h1=new H1("Registeration");



    TextField email =new TextField("Email");

    TextField user =new TextField("User Name");

    TextField pass =new TextField("Password");
    TextField confpass =new TextField("Confirm Password");

    Button registerButton = new Button("Register");






    public RegisterView(){

        setClassName("register");

        registerButton.addClickListener(buttonClickEvent -> {
            String postEndpoint = "http://api.cup2022.ir/api/v1/user";
            JSONObject obj = new JSONObject();
            obj.put("email", email.getValue());
            obj.put("password", pass.getValue());
            obj.put("passwordConfirm", confpass.getValue());
            obj.put("name", user.getValue());

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

            registerButton.getUI().ifPresent(UI -> UI.navigate("login"));
        });

        add(h1,email,user,pass,confpass, registerButton);

    }


}