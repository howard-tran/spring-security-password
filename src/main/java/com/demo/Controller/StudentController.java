package com.demo.Controller;

import com.demo.Model.Student;
import com.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class StudentController {
  private StudentService studentService;

  @Autowired
  StudentController(StudentService personService) {
    this.studentService = personService;
  }

  @PostMapping("/add")
  public Response<Object> AddStudent(@RequestBody Student person) {
    // validation check
    if (person.getUsername().length() == 0) {
      return new Response<Object>("no-empty", ErrorType.OK);
    }
    if (person.getUsername().matches(".*\\s+.*")) {
      return new Response<Object>("no-white-space", ErrorType.OK);
    }
    if (!person.getUsername().matches("^[a-zA-Z0-9]*$")) {
      return new Response<Object>("no-special-character", ErrorType.OK);
    }

    // client error handle
    if (this.studentService.AddStudent(person).get()) {
      return new Response<Object>("ok", ErrorType.OK);
    } else {
      return new Response<Object>("username-not-available", ErrorType.OK);
    }
  }

  @GetMapping("/getall")
  public Response<Object> GetAllStudent() {
    return new Response<Object>(
      this.studentService.GetAll().get(),
      ErrorType.OK
    );
  }

  @GetMapping("/get")
  public Response<Object> GetStudentByUsername(
    @RequestParam(name = "username", required = true) String username
  ) {
    return new Response<Object>(
      this.studentService.GetStudentByUsername(username).get(),
      ErrorType.OK
    );
  }

  @GetMapping("/find")
  public Response<Object> FindStudent(
    @RequestParam(name = "searchkey", required = true) String searchKey
  ) {
    if (!searchKey.matches("^[a-zA-Z0-9]*$")) {
      return new Response<Object>("no-special-character", ErrorType.OK);
    }
    return new Response<Object>(
      this.studentService.FindStudent(searchKey).get(),
      ErrorType.OK
    );
  }
}
