package com.exception.service;

import com.exception.entity.Student;
import com.exception.exception.ResourceNotFound;
import com.exception.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
  private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        Student save = studentRepository.save(student);
        return save;
    }
    public void deleteStudentById(long id){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("record is not found with this id" + id)
        );
        studentRepository.deleteById(id);
        return;
    }
    public Student getStudentById(long id){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("record is not found with this id" + id)
        );
        studentRepository.findById(id).get();
        return student;
    }

    public Student updateStudentById(long id, Student student) {
        Student info = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("record is not found with this id" + id)
        );
       studentRepository.findById(id).get();
        info.setName(student.getName());
        info.setCourse(student.getCourse());
        info.setFee(student.getFee());
        Student save = studentRepository.save(info);
      return save;
    }

    public List<Student> getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort.Direction direction = Sort.Direction.ASC;

        if (sortDir != null && sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) {
            direction = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(direction, sortBy);
        PageRequest request = PageRequest.of(pageNo, pageSize, sort);

        Page<Student> all = studentRepository.findAll(request);
        List<Student> studentList = all.getContent();

        // Using Stream API to collect into a List without transformation
        List<Student> collect = studentList.stream().collect(Collectors.toList());

        return collect;
    }


}
