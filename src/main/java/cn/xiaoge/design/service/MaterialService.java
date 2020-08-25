package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.Material;
import cn.xiaoge.design.entity.vo.PageBean;




public interface MaterialService {

    /**
    * 添加材料
    *
    * @param material 材料
    */
    void add(Material material);

    /**
    * 删除材料
    *
    * @param id 材料id
    */
    void deleteById(Integer id);

    /**
    * 修改不为空的属性
    *
    * @param material 材料
    */
    void updateNotNull(Material material) ;

    /**
    * 分页查询
    *
    * @param page 页数
    * @param size 页面大小
    * @param order 排序
    * @param searchKey 搜索的属性
    * @return 分页信息
    */
    PageBean<Material> findAll(Integer page,String searchKey,String order,Integer size);


    /**
    * 查询单个对象
    * @param id 主键
    */
    Material findById(Integer id);

}
