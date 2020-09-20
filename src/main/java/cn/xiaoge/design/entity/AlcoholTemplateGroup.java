package cn.xiaoge.design.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * (AlcoholTemplateGroup)实体类
 *
 * @author makejava
 * @since 2020-09-14 16:43:18
 */
@Entity
@Data
@Table(name = "alcohol_template_group")
public class AlcoholTemplateGroup implements Serializable {

    @Id
    private Integer id;

    @Column(length = 10)
    private String name;

}