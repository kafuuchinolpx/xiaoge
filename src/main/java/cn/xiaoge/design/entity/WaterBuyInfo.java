package cn.xiaoge.design.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//订单
@Entity
@Data
@Table(name = "water_buy_info")
public class WaterBuyInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //文件
    @Column(length = 20)
    private String file;

    //详细信息
    @Type(type = "text")
    private String info;

    //备注
    private String remark;

    //用户
    private Integer userId;
    //支付状态
    @Column(length = 11)
    private String payStatus;

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
