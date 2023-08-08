package ru.martynov.MyTaskMaster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.martynov.MyTaskMaster.exception.NotFoundException;
import ru.martynov.MyTaskMaster.models.Person;
import ru.martynov.MyTaskMaster.repository.PersonRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public String getUsername(Long id) {
        return getUserById(id).getUsername();
    }

    @Override
    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public void setUsername(Long id, String newUsername) {
        Person person = getUserById(id);
        person.setUsername(newUsername);
        personRepository.save(person);
    }

    private Person getUserById(long id) {
        return personRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Нет пользователя с id=" + id + "."));
    }
}
