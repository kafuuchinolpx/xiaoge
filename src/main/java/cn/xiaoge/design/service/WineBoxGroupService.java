package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.WineBoxGroup;

import java.util.List;


public interface WineBoxGroupService {

    /**
     * 添加酒模板
     *
     * @param wineBoxGroup 酒模板
     */
    void add(WineBoxGroup wineBoxGroup);

    /**
     * 删除酒模板
     *
     * @param id 酒模板id
     */
    void deleteById(Integer id);

    /**
     * 修改不为空的属性
     *
     * @param wineBoxGroup 酒模板
     */
    void updateNotNull(WineBoxGroup wineBoxGroup);

    /**
     * 分页查询
     *
     * @return 分页信息
     */
    List<WineBoxGroup> findAll();

    /**
     * 查询单个对象
     *
     * @param id 主键
     */
    WineBoxGroup findById(Integer id);


}
