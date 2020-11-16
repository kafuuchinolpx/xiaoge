package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.AppUser;
import cn.xiaoge.design.entity.UserAddress;
import cn.xiaoge.design.entity.vo.PageBean;

import java.util.List;


public interface UserAddressService {

    /**
     * 添加应用用户
     *
     * @param userAddress 应用用户
     */
    void add(UserAddress userAddress);

    /**
     * 删除应用用户
     *
     * @param id 应用用户id
     */
    void deleteById(Integer id);

    /**
     * 查询单个对象
     *
     * @param id 主键
     */
    List<UserAddress> findByUserId(Integer id);

}
