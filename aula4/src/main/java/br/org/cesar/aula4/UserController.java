package br.org.cesar.aula4;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    ArrayList<User> listaUsuarios = new ArrayList<User>();

    @GetMapping("/user")
    public User getUser(@RequestParam(value = "nome") String nome) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getNome().equalsIgnoreCase(nome)) {
                return listaUsuarios.get(i);
            }
        }
        return null;
    }

    @GetMapping("/users")
    public ArrayList<User> getUsers() {
        return listaUsuarios;
    }

    @PostMapping("/user")
    public User cadastrarUser(@RequestBody User usuario) {
        listaUsuarios.add(usuario);
        return usuario;
    }
}