package com.demo.Controller;

import com.demo.Model.Student;
import com.demo.Service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController {
  private CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @PostMapping("/add")
  public Response<Object> AddCourse(
    @RequestBody Student student,
    @RequestBody List<String> courses
  ) {
    return new Response<Object>(
      this.courseService.AddCourse(student, courses),
      ErrorType.OK
    );
  }

  @GetMapping("/getall")
  public Response<Object> GetAllCourse() {
    return new Response<Object>(this.courseService.GetAll(), ErrorType.OK);
  }

  @GetMapping("/get")
  public Response<Object> GetCourse(
    @RequestParam(name = "username", required = true) String username
  ) {
    return new Response<Object>(
      this.courseService.GetCourse(new Student(username)),
      ErrorType.OK
    );
  }
}
