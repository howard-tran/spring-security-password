package com.demo.Repository;

import com.demo.Model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository("FakePersonRepository")
public class FakeStudentimpl implements IStudent {
  private static List<Student> storage = new ArrayList<>();

  @Override
  public Student GetStudentById(String id) {
    return storage
      .stream()
      .filter(p -> p.getId().equals(id))
      .findAny()
      .orElse(null);
  }

  @Override
  public List<Student> SearchStudent(String searchKey) {
    return storage
      .stream()
      .filter(
        p -> p.getUsername().matches(String.format("(?i)(%s)(.*)", searchKey))
      )
      .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public Student GetStudentByUsername(String username) {
    return storage
      .stream()
      .filter(p -> p.getUsername().equals(username))
      .findAny()
      .orElse(null);
  }

  @Override
  public List<Student> GetAllStudent() {
    return FakeStudentimpl.storage;
  }

  @Override
  public void AddStudent(Student person) {
    FakeStudentimpl.storage.add(person);
  }

  @Override
  public void DeleteStudentOne(String id) {
    FakeStudentimpl.storage.removeIf(p -> p.getId().equals(id));
  }

  @Override
  public void DeleteStudentMany(List<String> idList) {
    FakeStudentimpl.storage.removeIf(
      p -> {
        return idList
          .stream()
          .filter(id -> id.equals(p.getId()))
          .findAny()
          .isPresent();
      }
    );
  }
}
