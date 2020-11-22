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
 * (Winebox)实体类
 *
 * @author makejava
 * @since 2020-11-21 15:45:35
 */

//酒模板
@Entity
@Data
@Table(name = "wine_box")
public class WineBox implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 11)
    private Integer deleteState;

    @Column(length = 11)
    private Integer boxTypeId;

    @Column(length = 11)
    private Integer recommend;

    @Column(length = 11)
    private Integer groupId;

    @Column(length = 10)
    private String remark;

    @Column(length = 50)
    private String image;

    @Column(length = 11)
    private Integer length;

    @Column(length = 11)
    private Integer materialId;

    @Column(length = 50)
    private String name;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deleteTime;

    @Type(type = "text")
    private String bodyStyle;


}