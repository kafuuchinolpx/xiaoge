package cn.xiaoge.design.service;
import cn.xiaoge.design.entity.Style;
import cn.xiaoge.design.entity.vo.PageBean;


public interface StyleService {

    /**
    * 添加用途
    *
    * @param style 用途
    */
    void add(Style style);

    /**
    * 删除用途
    *
    * @param id 用途id
    */
    void deleteById(Integer id);

    /**
    * 修改不为空的属性
    *
    * @param style 用途
    */
    void updateNotNull(Style style) ;

    /**
    * 分页查询
    *
    * @param page 页数
    * @param size 页面大小
    * @param order 排序
    * @param searchKey 搜索的属性
    * @return 分页信息
    */
    PageBean<Style> findAll(Integer page,String searchKey,String order,Integer size);


    /**
    * 查询单个对象
    * @param id 主键
    */
    Style findById(Integer id);

}
