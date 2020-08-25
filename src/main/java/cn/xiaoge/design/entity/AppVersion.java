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

//应用版本
@Entity
@Data
@Table(name = "app_version", indexes = {
})
public class AppVersion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //版本
    @Column(length = 11)
    private String versionName;





}
