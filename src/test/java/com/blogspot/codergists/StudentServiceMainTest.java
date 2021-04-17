package com.blogspot.codergists;

import com.blogspot.codergists.factory.StudentServiceFactory;
import com.blogspot.codergists.service.Student;
import com.blogspot.codergists.service.StudentRepository;
import com.blogspot.codergists.service.StudentService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // approach-1 to work with mockito
public class StudentServiceMainTest {

  @Mock
  StudentRepository studentRepository;

  @InjectMocks
  StudentService studentService;

  MockedStatic<StudentServiceFactory> mockedStatic;

  @BeforeEach
  public void setup() {
    //MockitoAnnotations.openMocks(this); //approach-2 to work with mockito
    mockedStatic = Mockito.mockStatic(StudentServiceFactory.class); //(1)  static method mocking
    mockedStatic.when(() -> StudentServiceFactory.getStudentService()).thenReturn(studentService);
  }

  @Test
  void studentServiceTest() {
    //given:
    Mockito.when(studentRepository.getById(Mockito.anyLong())).thenReturn(new Student());

    //when:
    Student student = studentService.getStudentById(1L);

    //then:
    Assert.assertNotNull(student);
  }

  @AfterEach
  public void cleanUp() {
    mockedStatic.close(); // must close to release mocks
  }

}
