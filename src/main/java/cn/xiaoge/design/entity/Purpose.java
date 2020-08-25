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

//用途
@Entity
@Data
@Table(name = "purpose")
public class Purpose implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //用途名
    @Column(length = 11)
    private String name;

    //备注
    @Type(type = "text")
    @Column(length = 11)
    private String remark;



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
