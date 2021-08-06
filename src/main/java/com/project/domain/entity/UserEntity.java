package com.project.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor   //기본 생성자
@Getter
@Entity
@EqualsAndHashCode(of = "id")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String loginId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;


    private boolean emailVerified;

    private String emailCheckToken;

    private LocalDateTime joinedAt;

    private String bio;

    private String url;

    private String job;

    private String city;

    @Lob @Basic(fetch = FetchType.EAGER )
    private String profileImage;

    private boolean moimCreatedByEmail;
    private boolean moimCreatedByWeb;

    private boolean moimEnrollmentResultByEmail;
    private boolean moimEnrollmentResultByWeb;

    private boolean moimUpadatedByEmail;
    private boolean moimUpdatedByWeb;


    @Builder
    public UserEntity(Long id, String loginId, String email, String password){
        this.id = id;
        this.loginId = loginId;
        this.email = email;
        this.password = password;
    }

}
