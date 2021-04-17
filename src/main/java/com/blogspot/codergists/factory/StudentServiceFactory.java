package com.blogspot.codergists.factory;

import com.blogspot.codergists.service.StudentRepository;
import com.blogspot.codergists.service.StudentService;

/**
 * Dummy Factory just to demonstrate Static Mocking
 */
public final class StudentServiceFactory {

  static StudentRepository studentRepository;

  public static StudentService getStudentService() {
    return new StudentService(studentRepository);
  }

}
