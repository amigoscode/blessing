package com.example.demo.person;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> getPersons() {
        return personService.getPeople();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Integer id) {
        Person person = personService.getPerson(id);
        return person;
    }

    @PostMapping
    public void createNewPerson(@RequestBody NewPersonRequest newPersonRequest) {
        System.out.println(newPersonRequest);
        personService.saveNewPersonRequest(newPersonRequest);
    }

    // implement delete functionality
    // DELETE -> localhost:8084/persons/1

    // implement update functionality
    // PUT -> localhost:8084/persons/1
    // allow client to update person name. e.g PersonUpdateRequest(name, age, password)

}
