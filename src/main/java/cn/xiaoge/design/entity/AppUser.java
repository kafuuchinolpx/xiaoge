package cn.xiaoge.design.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Index;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//应用用户
@Entity
@Data
@Table(name = "app_user")
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //用户名
    @Column(length = 11)
    private String name;

    //密码
    @Column(length = 11)
    @JsonIgnore
    private String password;

    //电话
    @Column(length = 11)
    private String telephone;

    //微信号
    @Column(length = 11)
    private String wxCode;

    //地址
    @Type(type = "text")
    @Column(length = 11)
    private String address;



    //创建时间
    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    //最后修改时间
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
