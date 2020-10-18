package com.example.demo.base.domain.memberInformation;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MemberForm {

    /**
     * 会員ID
     */
    private String memberId;

    /**
     * パスワード
     */
    private String password;

    /**
     * 会員名
     */
    private String memberName;

    /**
     * 権限
     */
    private String role;

    /**
     * 生年月日
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    /**
     * 年齢
     */
    private int age;

    /**
     * 電話番号
     */
    private int phoneNumber;

    /**
     * 住所
     */
    private String address;
}
