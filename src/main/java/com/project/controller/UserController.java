package com.project.controller;

import com.project.dto.form.UserForm;
import com.project.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/user/signup")
    public String signUpPage(Model model){
        if(!model.containsAttribute("userForm")) {
            model.addAttribute("userForm", new UserForm());
        }
        return "/form/uiSignUpForm";
    }

    @PostMapping("/user/signup")
    public String processSignUp(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm", bindingResult);
            redirectAttributes.addFlashAttribute("userForm", userForm);
            return "redirect:/user/signup";
        }
        userService.joinUser(userForm);  //질문
        return  "redirect:/user/signin";
    }

    @GetMapping("user/signin")
    public String signInPage(){
        return "/form/uiSignInForm";
    }

}
