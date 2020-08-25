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

//系统用户
@Entity
@Data
@Table(name = "system_user")
public class SystemUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //密码
    @JsonIgnore
    @Column(length = 50)
    private String password;

    //用户名
    @Column(unique = true,length = 11)
    private String name;





}
