package com.blogspot.codergists.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This tests runs with junit-vintage engine (JUnit4)
 * <p>
 * All tests must be public
 * Uses @RunWith(MockitoJUnitRunner.class)
 */
@RunWith(MockitoJUnitRunner.class)
public class VintageTest {

  @Mock
  StudentRepository studentRepository; // can be an interface, create mock object

  @InjectMocks
  StudentService studentService;

  @Spy
  Student studentSpy;//must be a class with no-arg constructor, it create real object

  @Before
  public void setUp() {
    //MockitoAnnotations.openMocks(this);

    Mockito.when(studentSpy.getName()).thenReturn("Thiru");

    Mockito.when(studentRepository.getById(Mockito.anyLong())).thenReturn(studentSpy);
  }

  @Test
  public void getStudentByIdTest() {

    //given: a service instance
    //StudentService studentService =new StudentService(studentRepository); Approach2: to inject mocks into real Objects.

    //when: invoking getStudentById
    Student student = studentService.getStudentById(1000L);

    //then: expect response from partially stubbed (Spy)
    assertEquals("Thiru", student.getName());// student is Spy ,  getName() value returned from Stubbing
    assertEquals(38, student.getAge());// student is Spy , getAge()  value returned from real method

  }
}
