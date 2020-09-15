package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.AlcoholTemplate;
import cn.xiaoge.design.entity.AlcoholTemplateGroup;
import cn.xiaoge.design.entity.vo.PageBean;

import java.util.List;


public interface AlcoholTemplateGroupService {

    /**
     * 添加酒模板
     *
     * @param alcoholTemplateGroup 酒模板
     */
    void add(AlcoholTemplateGroup alcoholTemplateGroup);

    /**
     * 删除酒模板
     *
     * @param id 酒模板id
     */
    void deleteById(Integer id);

    /**
     * 修改不为空的属性
     *
     * @param alcoholTemplateGroup 酒模板
     */
    void updateNotNull(AlcoholTemplateGroup alcoholTemplateGroup);

    /**
     * 分页查询
     *
     * @return 分页信息
     */
    List<AlcoholTemplateGroup> findAll();

    /**
     * 查询单个对象
     *
     * @param id 主键
     */
    AlcoholTemplateGroup findById(Integer id);
}
