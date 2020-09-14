package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.AlcoholTemplate;
import cn.xiaoge.design.entity.vo.PageBean;

import java.util.List;


public interface AlcoholTemplateService {

    /**
    * 添加酒模板
    *
    * @param alcoholTemplate 酒模板
    */
    void add(AlcoholTemplate alcoholTemplate);

    /**
    * 删除酒模板
    *
    * @param id 酒模板id
    */
    void deleteById(Integer id);

    /**
    * 修改不为空的属性
    *
    * @param alcoholTemplate 酒模板
    */
    void updateNotNull(AlcoholTemplate alcoholTemplate) ;

    /**
    * 分页查询
    *
    * @param page 页数
    * @param size 页面大小
    * @param order 排序

    * @return 分页信息
    */
    PageBean<AlcoholTemplate> findAll(Integer page,String order,Integer size);

    /**
    * 查询级联的对象
    * @param id 主键
    * @return 对象
    */
    AlcoholTemplate findByIdWithPurpose(Integer id);
    /**
    * 查询级联的对象
    * @param id 主键
    * @return 对象
    */
    AlcoholTemplate findByIdWithMaterial(Integer id);
    /**
    * 查询级联的对象
    * @param id 主键
    * @return 对象
    */
    AlcoholTemplate findByIdWithBoxType(Integer id);

    /**
    * 查询单个对象
    * @param id 主键
    */
    AlcoholTemplate findById(Integer id);

    List<AlcoholTemplate> findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer styleId, int length);

    List<AlcoholTemplate> findByBoxTypeIdAndLengthGreaterThan(Integer boxTypeId, int length);
}
