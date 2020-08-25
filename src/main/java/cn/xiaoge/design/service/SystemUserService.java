package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.SystemUser;
import cn.xiaoge.design.entity.vo.PageBean;




public interface SystemUserService {

    /**
    * 添加系统用户
    *
    * @param systemUser 系统用户
    */
    void add(SystemUser systemUser);

    /**
    * 删除系统用户
    *
    * @param id 系统用户id
    */
    void deleteById(Integer id);

    /**
    * 修改不为空的属性
    *
    * @param systemUser 系统用户
    */
    void updateNotNull(SystemUser systemUser) ;

    /**
    * 分页查询
    *
    * @param page 页数
    * @param size 页面大小
    * @param order 排序
    * @param searchKey 搜索的属性
    * @return 分页信息
    */
    PageBean<SystemUser> findAll(Integer page,String searchKey,String order,Integer size);


    /**
    * 查询单个对象
    * @param id 主键
    */
    SystemUser findById(Integer id);
    /**
    * 登录
    * @param loginName 登录名
    * @param loginPassword 密码
    * @return 用户信息
    */
    SystemUser login(String loginName, String loginPassword);

}
