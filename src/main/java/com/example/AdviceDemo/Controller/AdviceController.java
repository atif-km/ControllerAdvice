package com.example.AdviceDemo.Controller;


import com.example.AdviceDemo.bean.ErrorResponse;
import com.example.AdviceDemo.bean.Person;
import com.example.AdviceDemo.exception.AdviceException;
import com.example.AdviceDemo.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    public AdviceController(AdviceService adviceService){
        this.adviceService = adviceService;
    }


    @GetMapping(value = "/resource/{panno}")
    public ResponseEntity<Object> getMessage(@PathVariable String panno){
       Person p = adviceService.getPerson(panno);
       if (Objects.isNull(p)){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with PAN no "+panno+" is not present");
       }
        else return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @GetMapping(value = "/res/{panno}")
    public ResponseEntity<Object> getPersonA(@PathVariable String panno)
    {
      Person p=  adviceService.getPersonA(panno);
      return ResponseEntity.status(HttpStatus.OK).body(p);
    }



    @ExceptionHandler(AdviceException.class)
    public ResponseEntity<Object> handleAdviceException(AdviceException ex)
    {
        System.out.println("In exception handler");

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
