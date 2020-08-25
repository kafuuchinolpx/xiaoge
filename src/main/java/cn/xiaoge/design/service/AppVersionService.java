package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.AppVersion;
import cn.xiaoge.design.entity.vo.PageBean;




public interface AppVersionService {

    /**
    * 添加应用版本
    *
    * @param appVersion 应用版本
    */
    void add(AppVersion appVersion);

    /**
    * 删除应用版本
    *
    * @param id 应用版本id
    */
    void deleteById(Integer id);

    /**
    * 修改不为空的属性
    *
    * @param appVersion 应用版本
    */
    void updateNotNull(AppVersion appVersion) ;

    /**
    * 分页查询
    *
    * @param page 页数
    * @param size 页面大小
    * @param order 排序
    * @param searchKey 搜索的属性
    * @return 分页信息
    */
    PageBean<AppVersion> findAll(Integer page,String searchKey,String order,Integer size);


    /**
    * 查询单个对象
    * @param id 主键
    */
    AppVersion findById(Integer id);

}
