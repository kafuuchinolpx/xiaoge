package cn.xiaoge.design.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//酒模板
@Entity
@Data
@Table(name = "wine_box")
public class WineBox implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //模板名称
    @Column(length = 50)
    private String name;

    //编辑文本名字
    @Column(length = 10)
    private String remark;

    @Column(length = 1)
    private Integer recommend;

    //长度
    private Integer length;

    //图片
    @Column(length = 50)
    private String image;

    //用途
    private Integer purposeId;

    @Transient
    private Purpose purpose;

    //分组
    private Integer groupId;

    @Transient
    private WineBoxGroup wineBoxGroup;

    @Transient
    private List<WineBox> son;
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

    private Integer deleteState;

    //标题样式
    @Type(type = "text")
    private String header1Style;
    //标题样式
    @Type(type = "text")
    private String header2Style;
    //标题样式
    @Type(type = "text")
    private String bodyStyle;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deleteTime;

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
