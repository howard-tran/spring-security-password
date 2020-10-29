package com.demo.Repository;

import com.demo.Model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("FakePersonRepository")
public class FakeStudentImpl implements IStudent {
  private static List<Student> storage = new ArrayList<>();

  @Autowired
  public FakeStudentImpl() {
    storage.add(new Student(UUID.randomUUID().toString(), "ad", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "user", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "mudada", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "mancuso", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "ert123", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "tyu123", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "hermes", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "orphan", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "orgy", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "sheldon", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "cooper", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "raji", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "woower", "123"));
    storage.add(new Student(UUID.randomUUID().toString(), "penny", "123"));
    storage.add(
      new Student(UUID.randomUUID().toString(), "chandlerBing", "123")
    );
    storage.add(new Student(UUID.randomUUID().toString(), "monica", "123"));
  }

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
    return FakeStudentImpl.storage;
  }

  @Override
  public void AddStudent(Student person) {
    FakeStudentImpl.storage.add(person);
  }

  @Override
  public void DeleteStudentOne(String id) {
    FakeStudentImpl.storage.removeIf(p -> p.getId().equals(id));
  }

  @Override
  public void DeleteStudentMany(List<String> idList) {
    FakeStudentImpl.storage.removeIf(
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
