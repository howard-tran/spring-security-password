package com.demo.Repository;

import com.demo.Model.Course;
import com.demo.Model.Student;
import java.util.List;

public interface ICourse {
  void AddCourse(Course course);
  List<Course> GetAll();

  List<Course> GetCourse(Student student);
}
