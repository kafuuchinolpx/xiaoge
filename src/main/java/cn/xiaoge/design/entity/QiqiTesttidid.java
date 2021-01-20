package cn.xiaoge.design.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * (QiqiTesttidid)实体类
 *
 * @author makejava
 * @since 2021-01-10 11:20:58
 */
@Entity
@Data
@Table(name = "qiqi_testtidid")
public class QiqiTesttidid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String gdata;

    private String copyright;

    private String groupname;

    private String isOfficial;

    private String isShow;

    private String libfontid;

    private String name;

    private String sort;

    private String thumbnail;

    private String userId;


}