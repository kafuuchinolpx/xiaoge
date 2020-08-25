package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.Order;
import cn.xiaoge.design.entity.vo.PageBean;




public interface OrderService {

    /**
    * 添加订单
    *
    * @param order 订单
    */
    void add(Order order);

    /**
    * 删除订单
    *
    * @param id 订单id
    */
    void deleteById(Integer id);

    /**
    * 修改不为空的属性
    *
    * @param order 订单
    */
    void updateNotNull(Order order) ;

    /**
    * 分页查询
    *
    * @param page 页数
    * @param size 页面大小
    * @param order 排序

    * @return 分页信息
    */
    PageBean<Order> findAll(Integer page,String order,Integer size);

    /**
    * 查询级联的对象
    * @param id 主键
    * @return 对象
    */
    Order findByIdWithAppUser(Integer id);

    /**
    * 查询单个对象
    * @param id 主键
    */
    Order findById(Integer id);

}
