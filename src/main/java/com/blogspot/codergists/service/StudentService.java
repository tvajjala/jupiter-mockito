package com.blogspot.codergists.service;

public class StudentService {

  StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public Student getStudentById(Long id) {
    return studentRepository.getById(id);
  }

}
