package cn.xiaoge.design.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (WineBoxGroup)实体类
 *
 * @author makejava
 * @since 2020-09-14 16:43:18
 */
@Entity
@Data
@Table(name = "wine_box_group")
public class WineBoxGroup implements Serializable {

    @Id
    private Integer id;

    @Column(length = 10)
    private String name;

}