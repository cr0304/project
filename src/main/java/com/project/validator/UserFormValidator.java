package com.project.validator;

import com.project.dto.form.UserForm;
import com.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserFormValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {  //아이디 이메일 중복 체크

        UserForm userForm = (UserForm) errors;
        if(userRepository.existsByLoginId(userForm.getLoginId())){
            errors.rejectValue("loginId", "invalid.loginId", new Object[]{userForm.getLoginId()}, "이미 사용중인 아이디입니다.");
        }
        if (userRepository.existsByEmail(userForm.getEmail())){
            errors.rejectValue("email", "invalid.email", new Object[]{userForm.getEmail()}, "이미 사용중인 이메일입니다.");
        }
    }
}
