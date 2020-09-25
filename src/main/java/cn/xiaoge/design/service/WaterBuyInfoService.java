package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.WaterBuyInfo;
import cn.xiaoge.design.entity.vo.PageBean;


public interface WaterBuyInfoService {

    /**
     * 添加订单
     *
     * @param waterBuyInfo 订单
     */
    void add(WaterBuyInfo waterBuyInfo);

    /**
     * 删除订单
     *
     * @param id 订单id
     */
    void deleteById(Integer id);

    /**
     * 修改不为空的属性
     *
     * @param waterBuyInfo 订单
     */
    void updateNotNull(WaterBuyInfo waterBuyInfo);

    /**
     * 分页查询
     *
     * @param page         页数
     * @param size         页面大小
     * @param waterBuyInfo 排序
     * @return 分页信息
     */
    PageBean<WaterBuyInfo> findAll(Integer page, String waterBuyInfo, Integer size);

    /**
     * 查询级联的对象
     *
     * @param id 主键
     * @return 对象
     */
    WaterBuyInfo findByIdWithAppUser(Integer id);

    /**
     * 查询单个对象
     *
     * @param id 主键
     */
    WaterBuyInfo findById(Integer id);

}
