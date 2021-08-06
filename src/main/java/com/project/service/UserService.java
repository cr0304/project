package com.project.service;

import com.project.domain.Role;
import com.project.dto.form.UserForm;
import com.project.domain.entity.UserEntity;
import com.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public Long joinUser(UserForm userForm){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userForm.newUserPassword(passwordEncoder.encode(userForm.getPassword()));
        return userRepository.save(userForm.toEntity()).getId();   //질문 왜 id를 리턴하는지
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException{
        Optional<UserEntity> userEntityWrapper = userRepository.findByLoginId(loginId);   //null일 수도 있어서.
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();  //SimpleGrantedAuthority?

        if (("cr0304").equals(loginId)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }

        return new User(userEntity.getLoginId(), userEntity.getPassword(), authorities);
    }


    // true - 사용 가능, 중복 없음
    // FALSE - 사용 불가능, 중복이 있음
   /* public boolean validateDuplicateLoginId(String loginId) {
        List<UserEntity> findMembers =
                userRepository.findUserEntityByLoginId(loginId);
        if (!findMembers.isEmpty()) {
           return false;
        }
        else{
            return true;
        }

    }
*/

}
