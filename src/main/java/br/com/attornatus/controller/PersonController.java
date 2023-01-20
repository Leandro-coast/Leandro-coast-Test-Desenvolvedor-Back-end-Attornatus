package br.com.attornatus.controller;

import br.com.attornatus.model.Person;
import br.com.attornatus.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/person")
    public ResponseEntity<Person> putPerson(@RequestBody Person person) {
        System.out.println(person.getName());
        try {
            return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") int id) {
        Optional<Person> person = personRepository.findById(id);

        return person.isPresent() ? new ResponseEntity<>(person.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPerson() {
        try {
        List<Person> personList = personRepository.findAll();

        return personList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(personList, HttpStatus.OK);

    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
