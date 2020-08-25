package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.BoxType;
import cn.xiaoge.design.entity.vo.PageBean;




public interface BoxTypeService {

    /**
    * 添加盒型
    *
    * @param boxType 盒型
    */
    void add(BoxType boxType);

    /**
    * 删除盒型
    *
    * @param id 盒型id
    */
    void deleteById(Integer id);

    /**
    * 修改不为空的属性
    *
    * @param boxType 盒型
    */
    void updateNotNull(BoxType boxType) ;

    /**
    * 分页查询
    *
    * @param page 页数
    * @param size 页面大小
    * @param order 排序
    * @param searchKey 搜索的属性
    * @return 分页信息
    */
    PageBean<BoxType> findAll(Integer page,String searchKey,String order,Integer size);


    /**
    * 查询单个对象
    * @param id 主键
    */
    BoxType findById(Integer id);

}
