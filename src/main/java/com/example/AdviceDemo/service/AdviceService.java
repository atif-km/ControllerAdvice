package com.example.AdviceDemo.service;

import com.example.AdviceDemo.bean.Person;
import com.example.AdviceDemo.exception.AdviceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdviceService {

    public static List<Person> personList;

    static {
        personList = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
        {
            Person person = new Person();
            person.setPanNo("PAN" + i);
            person.setNamme("Name" + i);
            person.setAddress("Address" + i);
            personList.add(person); }
    }


    public Person getPerson(String panNo){
        Optional<Person> personOptional = personList.stream().filter(p->p.getPanNo().equalsIgnoreCase(panNo)).findAny();
           return personOptional.orElse(null);
    }

    public Person getPersonA(String panno){
        Optional<Person> person = personList.stream().filter(p->p.getPanNo().equalsIgnoreCase(panno)).findAny();
        return person.orElseThrow(() -> new AdviceException("Person with PAN " + panno + " not found"));
    }

    public List<Person> getPersonList(){
        return personList;
    }

}
