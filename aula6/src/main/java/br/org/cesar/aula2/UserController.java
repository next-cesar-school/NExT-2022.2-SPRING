package br.org.cesar.aula2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiv1")
public class UserController {

    List<User> users = new ArrayList<User>();
    long idGenerated = 1;

    @GetMapping("/user")
    public List<User> listaUsers() {
        return this.users;
    }

    @GetMapping("/user/{varId}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "varId") long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return new ResponseEntity<User>(users.get(i), HttpStatus.OK);// status 200
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);// status 404
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User entity) {
        entity.setId(this.idGenerated++);
        this.users.add(entity);
        return new ResponseEntity<User>(entity, HttpStatus.CREATED);
    }

    @PutMapping("/user/{varId}")
    public ResponseEntity<User> updateUserById(
            @PathVariable(value = "varId") long id,
            @RequestBody User entity) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.get(i).setNome(entity.getNome());
                users.get(i).setCpf(entity.getCpf());
                return new ResponseEntity<User>(users.get(i), HttpStatus.OK);// status 200
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);// status 404
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                this.users.remove(users.get(i));
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);// status 404
    }
}
