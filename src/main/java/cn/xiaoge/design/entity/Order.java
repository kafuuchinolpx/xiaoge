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

//订单
@Entity
@Data
@Table(name = "user_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //用户

    private Integer userId;

    @Transient
    private AppUser appUser;
    //支付状态
    @Column(length = 11)
    private String payStatus;

    //详细信息
    @Type(type = "text")
    @Column(length = 11)
    private String info;

    //酒详细
    @Type(type = "text")
    @Column(length = 11)
    private String wineParameters;

    //文件1
    @Column(length = 11)
    private String file1;

    //文件2
    @Column(length = 11)
    private String file2;


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
