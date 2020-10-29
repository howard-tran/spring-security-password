package com.demo.Repository;

import com.demo.Model.Course;
import com.demo.Model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("FakeCourseRepository")
public class FakeCourseImpl implements ICourse {
  private static List<Course> storage = new ArrayList<>();

  @Autowired
  public FakeCourseImpl() {}

  @Override
  public void AddCourse(Course course) {
    storage.add(course);
  }

  @Override
  public List<Course> GetAll() {
    return storage;
  }

  @Override
  public List<Course> GetCourse(Student student) {
    return storage
      .stream()
      .filter(t -> t.getUsername().equals(student.getUsername()))
      .collect(Collectors.toCollection(ArrayList::new));
  }
}
