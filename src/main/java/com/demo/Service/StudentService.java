package com.demo.Service;

import com.demo.Model.Student;
import com.demo.Repository.IStudent;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
  private IStudent studentRespository;

  @Autowired
  public StudentService(
    @Qualifier("FakePersonRepository") IStudent personRespository
  ) {
    this.studentRespository = personRespository;
  }

  public Optional<Boolean> AddStudent(Student person) {
    if (this.GetStudentByUsername(person.getUsername()).isPresent()) {
      return Optional.of(false);
    }
    person.setId(UUID.randomUUID().toString());

    this.studentRespository.AddStudent(person);
    return Optional.of(true);
  }

  public Optional<List<Student>> GetAll() {
    return Optional.of(this.studentRespository.GetAllStudent());
  }

  public Optional<Boolean> CheckAvailableStudent(String username) {
    return Optional.of(
      (this.studentRespository.GetStudentByUsername(username) != null)
    );
  }

  public Optional<Student> GetStudentByUsername(String username) {
    return Optional.ofNullable(
      this.studentRespository.GetStudentByUsername(username)
    );
  }

  public Optional<List<Student>> FindStudent(String searchKey) {
    return Optional.ofNullable(this.studentRespository.SearchStudent(searchKey));
  }
}
