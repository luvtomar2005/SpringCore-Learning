package com.springmvc.controller;
import org.springframework.web.bind.annotation.PathVariable;
import com.springmvc.model.Student;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class HomeController {

    @RequestMapping("/register")
    public String showForm(Model model) {

        model.addAttribute("student", new Student());

        return "register";
    }

    @PostMapping("/processForm")
    public String processForm(
            @Valid
            @ModelAttribute("student") Student student,
            BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        return "success";
    }
    @RequestMapping("/student/{id}")
    public String getStudent(@PathVariable int id, Model model) {

        model.addAttribute("id", id);

        return "student";
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/save")
    public String save() {
        return "success";
    }
}