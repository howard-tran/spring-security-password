package com.demo.Model;

import java.util.List;

import javax.validation.constraints.NotBlank;


public class Course {
  private String id;

  @NotBlank
  private String username;

  @NotBlank
  private List<String> courses; 

  public Course() {
    
  }

  public Course(String username, List<String> courses) {
    this.username = username;
    this.courses = courses;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getCourses() {
    return this.courses;
  }

  public void setCourses(List<String> courses) {
    this.courses = courses;
  }
  
}
