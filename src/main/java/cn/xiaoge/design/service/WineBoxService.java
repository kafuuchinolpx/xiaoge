package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.WineBox;

import java.util.List;

/**
 * (Winebox)表服务接口
 *
 * @author makejava
 * @since 2020-11-21 15:45:35
 */
public interface WineBoxService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WineBox findAllById(Integer id);

    /**
     * 查询多条数据
     * @return 对象列表
     */
    List<WineBox> findAll();

    /**
     * 新增数据
     *
     * @param winebox 实例对象
     * @return 实例对象
     */
    WineBox insert(WineBox winebox);

    /**
     * 修改数据
     *
     * @param winebox 实例对象
     * @return 实例对象
     */
    WineBox update(WineBox winebox);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    void deleteById(Integer id);

}