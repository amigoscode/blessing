package com.example.demo.person;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> getPeople() {
        return personRepository.findAll().stream()
                .map(person -> new PersonDTO(
                        person.getId(),
                        person.getName(),
                        person.getAge(),
                        person.getEmail()))
                .toList();
    }

    public Person getPerson(Integer id) {
        // check if id is present
        // if not present throw an error
        // otherwise return the person
        if (personRepository.existsById(id)) {
            return personRepository.findById(id).get();
        }
        return null;
    }

    public void saveNewPersonRequest(NewPersonRequest newPersonRequest) {
        // check if emails taken
        // check if name or age are null
        if ((newPersonRequest.getName() == null || newPersonRequest.getName().isEmpty()) ||
                newPersonRequest.getAge() == null) {
            return;
        }

        boolean existsEmail = personRepository
                .findAll()
                .stream()
                .anyMatch(p -> newPersonRequest.getEmail().equals(p.getEmail()));

        if (existsEmail) {
            return;
        }

        Person person = new Person();
        person.setName(newPersonRequest.getName());
        person.setAge(newPersonRequest.getAge());
        person.setEmail(newPersonRequest.getEmail());

        personRepository.save(person);

    }
}
