package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.AppUser;
import cn.xiaoge.design.entity.vo.PageBean;




public interface AppUserService {

    /**
    * 添加应用用户
    *
    * @param appUser 应用用户
    */
    void add(AppUser appUser);

    /**
    * 删除应用用户
    *
    * @param id 应用用户id
    */
    void deleteById(Integer id);

    /**
    * 修改不为空的属性
    *
    * @param appUser 应用用户
    */
    void updateNotNull(AppUser appUser) ;

    /**
    * 分页查询
    *
    * @param page 页数
    * @param size 页面大小
    * @param order 排序

    * @return 分页信息
    */
    PageBean<AppUser> findAll(Integer page,String order,Integer size);


    /**
    * 查询单个对象
    * @param id 主键
    */
    AppUser findById(Integer id);

    AppUser findByTelephoneAndPassword(String telephone, String password);
}
