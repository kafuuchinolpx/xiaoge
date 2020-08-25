package cn.xiaoge.design.entity;

import java.io.Serializable;

/**
 * (Water)实体类
 *
 * @author makejava
 * @since 2020-08-25 16:42:11
 */
public class Water implements Serializable {
    private static final long serialVersionUID = 353860199727601739L;

    private Integer id;

    private Integer boxTypeId;

    private Object createTime;

    private String image;

    private Integer length;

    private Integer materialId;

    private String name;

    private Integer purposeId;

    private Object updateTime;

    private Object bodyStyle;

    private Object header1style;

    private Object header2style;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBoxTypeId() {
        return boxTypeId;
    }

    public void setBoxTypeId(Integer boxTypeId) {
        this.boxTypeId = boxTypeId;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(Integer purposeId) {
        this.purposeId = purposeId;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(Object bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public Object getHeader1style() {
        return header1style;
    }

    public void setHeader1style(Object header1style) {
        this.header1style = header1style;
    }

    public Object getHeader2style() {
        return header2style;
    }

    public void setHeader2style(Object header2style) {
        this.header2style = header2style;
    }

}