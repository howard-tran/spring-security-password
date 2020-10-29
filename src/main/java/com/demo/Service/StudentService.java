package com.demo.Service;

import com.demo.Model.Student;
import com.demo.Repository.IStudent;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
  private IStudent studentRepository;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public StudentService(
    @Qualifier("FakePersonRepository") IStudent personRespository,
    PasswordEncoder passwordEncoder
  ) {
    this.studentRepository = personRespository;
    this.passwordEncoder = passwordEncoder; 
  }

  public Optional<Boolean> AddStudent(Student person) {
    if (this.GetStudentByUsername(person.getUsername()).isPresent()) {
      return Optional.of(false);
    }
    person.setId(UUID.randomUUID().toString());

    this.studentRepository.AddStudent(person);
    return Optional.of(true);
  }

  public Optional<List<Student>> GetAll() {
    List<Student> res = this.studentRepository.GetAllStudent();
    for (Student student : res) {
      student.setPassword(this.passwordEncoder.encode(student.getPassword()));
    }
    return Optional.of(res);
  }

  public Optional<Boolean> CheckAvailableStudent(String username) {
    return Optional.of(
      (this.studentRepository.GetStudentByUsername(username) != null)
    );
  }

  public Optional<Student> GetStudentByUsername(String username) {
    Student student = this.studentRepository.GetStudentByUsername(username); 

    if (student == null) {
      return Optional.empty(); 
    }
    student.setPassword(this.passwordEncoder.encode(student.getPassword()));

    return Optional.of(student); 
  }

  public Optional<List<Student>> FindStudent(String searchKey) {
    List<Student> res = this.studentRepository.SearchStudent(searchKey);
    for (Student student : res) {
      student.setPassword(this.passwordEncoder.encode(student.getPassword()));
    }
    return Optional.of(res);
  }
}
