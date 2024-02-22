package a2_276.a2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import a2_276.a2.models.Student;
import a2_276.a2.models.StudentRepository;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    
    @GetMapping("/student/hub")
    public String getMethodName(Model model) {
        System.out.println("Getting all students");
        // get all students from the database
        List<Student> students = studentRepo.findAll();

        model.addAttribute("students", students);

        return "student/studentHub";
    }
    
    @GetMapping("/fetchStudent/{id}")
    @ResponseBody
    public ResponseEntity<Student> fetchStudent(@PathVariable("id") int id) {
        return studentRepo.findById(id)
                .map(ResponseEntity::ok)  // if student is found, return 200 OK with student data
                .orElse(ResponseEntity.notFound().build());  // if not found, return 404 Not Found
    }

    @PostMapping("/student/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response) {
        System.out.println("Adding student");

        String name = newStudent.get("name");
        String hairColor = newStudent.get("hairColor");
        String shape = newStudent.get("shape");
        int height = Integer.parseInt(newStudent.get("height"));
        int weight = Integer.parseInt(newStudent.get("weight"));
        double gpa = Double.parseDouble(newStudent.get("gpa"));
        
        studentRepo.save(new Student(name, hairColor, shape, height, weight, gpa));
        response.setStatus(201);

        return "redirect:/student/hub";
    }

    @PostMapping("/student/delete")
    public String deleteStudent(@RequestParam("delete-id") int id) {
        if(id>0)
            studentRepo.deleteById(id);
        return "redirect:/student/hub"; 
    }

    @PostMapping("/student/update")
    public String updateStudent(@RequestParam Map<String, String> newStudent, @RequestParam("update-id-form") int id, HttpServletResponse response) {
        System.out.println("Updating student");

        String name = newStudent.get("name");
        String hairColor = newStudent.get("hairColor");
        String shape = newStudent.get("shape");
        int height = Integer.parseInt(newStudent.get("height"));
        int weight = Integer.parseInt(newStudent.get("weight"));

        Student student = studentRepo.findById(id).orElse(null);
        if (student != null) {
            if (!name.isEmpty()) {
                student.setName(name);
            }
            if (!hairColor.isEmpty()) {
                student.setHairColor(hairColor);
            }
            if (!shape.isEmpty()) {
                student.setShape(shape);
            }
            if (height > 0) {
                student.setHeight(height);
            }
            if (weight > 0) {
                student.setWeight(weight);
            }
            if (!newStudent.get("gpa").isEmpty()) {
                double gpa = Double.parseDouble(newStudent.get("gpa"));
                student.setGpa(gpa);
            }
            studentRepo.save(student);
            response.setStatus(201);
        }

        return "redirect:/student/hub";
    }
        
    

    
    
}
