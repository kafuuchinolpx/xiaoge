package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.AlcoholTemplate;
import cn.xiaoge.design.entity.Water;

import java.util.List;

/**
 * (Water)表服务接口
 *
 * @author makejava
 * @since 2020-08-25 16:42:11
 */
public interface WaterService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Water queryById(Integer id);

    /**
     * 新增数据
     *
     * @param water 实例对象
     * @return 实例对象
     */
    Water insert(Water water);

    /**
     * 修改数据
     *
     * @param water 实例对象
     * @return 实例对象
     */
    Water update(Water water);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Object findAll(Integer page, String order, Integer size);


    Water findById(Integer id);

    void updateNotNull(Water water);

    List<Water> findAll();

    List<Water> findByPurposeIdAndLengthGreaterThan(Integer purposeId, int length);
}