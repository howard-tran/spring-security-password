package com.demo.Service;

import com.demo.Model.Course;
import com.demo.Model.Student;
import com.demo.Repository.ICourse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
  private ICourse courseRepository;

  @Autowired
  CourseService(@Qualifier("FakeCourseRepository") ICourse courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Optional<Boolean> AddCourse(
    Student student,
    List<String> courseNames
  ) {
    Course course = new Course(
      UUID.randomUUID().toString(),
      student.getUsername(),
      courseNames
    );
    this.courseRepository.AddCourse(course);
    return Optional.of(true);
  }

  public Optional<List<Course>> GetAll() {
    return Optional.of(this.courseRepository.GetAll());
  }

  public Optional<List<Course>> GetCourse(Student student) {
    return Optional.ofNullable(this.courseRepository.GetCourse(student));
  }
}
