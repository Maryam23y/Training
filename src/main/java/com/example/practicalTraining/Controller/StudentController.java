package com.example.practicalTraining.Controller;
import com.example.practicalTraining.Model.Student;
import com.example.practicalTraining.Repository.StudentRepository;
import com.example.practicalTraining.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")

public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    //get all API
    @GetMapping("/students")
    public List<Student> getAllEmployee(){
        return studentRepository.findAll();
    }
    //create mployee rest API

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student){

        return studentRepository.save(student);
    }

    //get mployee byId rest API
    @GetMapping("/students/{id}")

    public ResponseEntity<Student> getEmployeeById(@PathVariable Integer id){
        Student employee = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:"+id));
        return ResponseEntity.ok(employee);
    }
    //update employee rest API
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student studentDetails){
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exis with id:"+id));

        student.setName(studentDetails.getName());
         student.setAddress(studentDetails.getAddress());
         student.setCourse(studentDetails.getCourse());
         student.setPhone(studentDetails.getPhone());
        Student updateStudent = studentRepository.save(student);
        return ResponseEntity.ok(updateStudent);

    }

    @DeleteMapping("/students/{id}")

    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Integer id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exis with id:"+id));
        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }


}
