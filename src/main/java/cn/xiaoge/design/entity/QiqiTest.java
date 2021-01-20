package cn.xiaoge.design.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (QiqiTest)实体类
 *
 * @author makejava
 * @since 2021-01-10 11:20:56
 */
@Entity
@Data
@Table(name = "qiqi_test")
public class QiqiTest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer bleed;

    private Integer categoryid;

    private String categoryname;

    private Integer categorysubid;

    private String categorysubname;

    private Integer coinprice;

    private String cover;

    private String coverid;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    private Integer defaultpage;

    private Integer designid;

    private Integer designerid;

    private Integer dpi;

    private Integer editcount;

    private Integer editfrom;

    private String extinfo;

    private Integer favoritecount;

    private Integer fontlist;

    private Integer height;

    private Integer industryid;

    private Integer industrysubid;

    private Integer iscalendar;

    private Integer isdel;

    private Integer ismine;

    private Integer isofficial;

    private Integer isprint;

    private Integer jobguid;

    private String keywords;

    private String notes;

    @Transient
    private List<QiqiTestPages> pages;

    private Integer pagesId;

    private Integer productid;

    private String reason;

    private Integer saletype;

    private String shareurlcode;

    private Integer sort;

    private Integer splitdirection;

    private Integer splitnum;

    private Integer status;

    private Integer styleid;

    private String title;

    private String unit;

    //最后修改时间
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatetime;

    private Integer viewcount;

    private Integer width;

    private String wxconvet;

}