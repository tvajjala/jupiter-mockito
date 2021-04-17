package com.blogspot.codergists.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This tests runs with junit-jupiter engine (Junit5)
 * <p>
 * All tests need not be public
 * uses @ExtendWith(MockitoExtension.class)
 */
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

  @Mock
  StudentRepository studentRepository; // can be an interface, create mock object

  @InjectMocks
  StudentService studentService;
// StudentService service is real object, which has Parameterize Constructor i.e StudentService(StudentRepository studentRepository)
  // Two ways you can create StudentService object
  // Approach-1:  Use @InjectMocks annotation on this class which will create instance and Injects StudentRepository Mock into it
  // Approach-2: Use StudentService studentService =new StudentService(studentRepository);  manually create with new clause and pass Mocked studentRepository

  @Spy
  Student studentSpy;//must be a class with no-arg constructor, it create real object

  @BeforeEach
  void setUp() {

    //studentSpy= Mockito.spy(new Student(1000L)); // Create a Spy object without using @Spy annotation.

    //Spy on POJOs/ Implementation classes(if they available to your module on compile time),
    // if want to do partial stubbing and partial real calls
    // Try to avoid Spy if Possible, unless you really want to test time behavior in unitTests
    //(RealTime behaviour such as like DB Calls, /real API which takes long time / that works only on real environment)
    //Spy-Stubbing
    Mockito.when(studentSpy.getName()).thenReturn("Thiru");

    //Mock Stubbing, if your project depends on interfaces(API), implementation classes(external libraries) available runtime.
    Mockito.when(studentRepository.getById(Mockito.anyLong())).thenReturn(studentSpy);
  }

  @Test
  void getStudentByIdTest() {

    //given: a service instance
    //StudentService studentService =new StudentService(studentRepository); Approach2: to inject mocks into real Objects.

    //when: invoking getStudentById
    Student student = studentService.getStudentById(1000L);

    //then: expect response from partially stubbed (Spy)
    assertEquals("Thiru", student.getName());// student is Spy ,  getName() value returned from Stubbing
    assertEquals(38, student.getAge());// student is Spy , getAge()  value returned from real method

  }
}