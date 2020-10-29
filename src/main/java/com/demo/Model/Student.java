package com.demo.Model;

import javax.validation.constraints.NotBlank;

public class Student {
  private String id;

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  public Student() {}

  public Student(String id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public Student(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Student(String username) {
    this.username = username;
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

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
