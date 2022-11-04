package br.org.cesar.aula3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.cesar.aula3.entities.User;

@RestController
public class UserController {

    @GetMapping("/user")
    public String getUser(
            @RequestParam(value = "name", defaultValue = "fulano") String name,
            @RequestParam(value = "idade", defaultValue = "100") int idade) {
        return "usuario " + name + " com " + idade + " anos.";
    }
    // http://localhost:8080/user?idade=30&name=gerson

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") long id) {
        return new User(id, "gerson", "123456789", "12345678");
    }

    @GetMapping("/user/path/{id}")
    public String getUserPathById(@PathVariable(value = "id") String id) {
        if (id.equalsIgnoreCase("47fec212dbed34b2e0cd86e9377b7dfc")) {
            return "autenticado";
        }
        return "usuario com id = " + id + " Não autenticado";
    }

}
