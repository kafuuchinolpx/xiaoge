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

//酒模板
@Entity
@Data
@Table(name = "alcohol_template")
public class AlcoholTemplate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //模板名称
    @Column(length = 50)
    private String name;

    //编辑文本名字
    @Column(length = 10)
    private String remark;

    //长度
    private Integer length;

    //图片
    @Column(length = 50)
    private String image;

    //用途

    private Integer purposeId;

    @Transient
    private Purpose purpose;
    //材料

    private Integer styleId;

    @Transient
    private Purpose style;
    //风格

    private Integer materialId;

    @Transient
    private Material material;
    //盒型

    private Integer boxTypeId;

    @Transient
    private BoxType boxType;

    //标题样式
    @Type(type = "text")
    private String header1Style;
    //标题样式
    @Type(type = "text")
    private String header2Style;
    //标题样式
    @Type(type = "text")
    private String bodyStyle;

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
