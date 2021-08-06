package com.project.dto.form;


import com.project.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Setter
@Getter
public class UserForm {

    @NotEmpty(message = "아이디는 필수입니다")
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[a-z0-9_-]{3,20}$")
    private String loginId;
    @NotEmpty(message = "이메일은 필수입니다")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotEmpty(message = "비밀번호는 필수입니다")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    public UserEntity toEntity(){    //리포지토리에 넘기기위한 메소드
        return UserEntity.builder()
                .loginId(loginId)
                .email(email)
                .password(password)
                .build();
    }

    public void newUserPassword(String password){
        this.password = password;
    }

}






