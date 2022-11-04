package br.org.cesar.aula2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/apiv2")
public class UserControllerJPA {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user")
	public List<User> listaUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/user/{varId}")
	public ResponseEntity<User> getUsersById(@PathVariable(value = "varId") long id) {
		Optional<User> resBusca = userRepository.findById(id);
		if (resBusca.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);// status 404
		} else {
			return new ResponseEntity<User>(resBusca.get(), HttpStatus.OK);// status 200
		}
	}

	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@RequestBody User entity) {
		User createdUser = userRepository.save(entity);
		return new ResponseEntity<User>(createdUser, HttpStatus.CREATED); // status 201
	}

	@PutMapping("/user/{varId}")
	public ResponseEntity<User> updateUserById(
			@PathVariable(value = "varId") long id,
			@RequestBody User entity) {
		Optional<User> resBusca = userRepository.findById(id);
		if (resBusca.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);// status 404
		} else {
			entity.setId(id);
			User updatedEntity = userRepository.save(entity);
			return new ResponseEntity<User>(updatedEntity, HttpStatus.OK);// status 200
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") long id) {
		Optional<User> resBusca = userRepository.findById(id);
		if (resBusca.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);// status 404
		} else {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);// status 200
		}
	}
}
