package br.com.attornatus;

import static org.junit.jupiter.api.Assertions.*;
import br.com.attornatus.controller.PersonController;
import br.com.attornatus.model.Person;
import br.com.attornatus.repository.PersonRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class AttornatusApplicationTests {
	@InjectMocks
	PersonController personController = new PersonController();

	@Mock
	PersonRepository personRepository;

	@Test
	void createPersonTest() {
		//MockitoAnnotations.initMocks(this);
		Person person = new Person();
		person.setName("sdasdas");
		person.setId(1);
		LocalDate date = LocalDate.parse("2020-01-08");
		person.setBirthDate(date);
		Mockito.when(personRepository.save(person)).thenReturn(person) ;
		assertEquals(person, personController.createPerson(person)); ;
	}

}
