package cn.xiaoge.design.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * (QiqiTestPages)实体类
 *
 * @author makejava
 * @since 2021-01-10 11:20:57
 */
@Entity
@Data
@Table(name = "qiqi_test_pages")
public class QiqiTestPages implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sort;

    private Integer templateid;

    private String thumbnail;

    private String thumbnailid;

    private String bgcolor;

    private String bgimgurl;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    private String elements;

    private String elementsjson;


}