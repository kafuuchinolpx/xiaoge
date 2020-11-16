package cn.xiaoge.design.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_address")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 11)
    private Integer userId;

    @Column(length = 20)
    private String phone;

    @Column(length = 255)
    private String address;

    private Integer defaultType;


}
