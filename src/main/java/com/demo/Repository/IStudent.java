package com.demo.Repository;

import com.demo.Model.Student;
import java.util.List;

public interface IStudent {
  Student GetStudentById(String id);
  Student GetStudentByUsername(String username);
  List<Student> SearchStudent(String searchKey);
  List<Student> GetAllStudent();

  void AddStudent(Student student);
  void DeleteStudentOne(String id);
  void DeleteStudentMany(List<String> idList);
}
