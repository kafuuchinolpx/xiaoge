package cn.xiaoge.design.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (Water)实体类
 *
 * @author makejava
 * @since 2020-08-25 16:42:11
 */
@Data
@Entity
public class Water implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //创建时间
    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    //图片
    @Column(length = 50)
    private String image;

    private Integer length;

    //模板名称
    @Column(length = 50)
    private String name;

    //用途

    private Integer purposeId;

    @Transient
    private Purpose purpose;

    //最后修改时间
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    //标题样式
    @Type(type = "text")
    private String bodyStyle;


    //标题样式
    @Type(type = "text")
    private String headerStyle;


}