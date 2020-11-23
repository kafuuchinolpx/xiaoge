package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.WineBox;
import cn.xiaoge.design.entity.vo.PageBean;

import java.util.List;


public interface WineBoxService {

    /**
     * 添加酒模板
     *
     * @param wineBox 酒模板
     */
    void add(WineBox wineBox);

    /**
     * 删除酒模板
     *
     * @param id 酒模板id
     */
    void deleteById(Integer id);

    /**
     * 修改不为空的属性
     *
     * @param wineBox 酒模板
     */
    void updateNotNull(WineBox wineBox);

    /**
     * 分页查询
     *
     * @param page  页数
     * @param size  页面大小
     * @param order 排序
     * @return 分页信息
     */
    PageBean<WineBox> findAll(Integer page, String order, Integer size);

    /**
     * 分页查询
     *
     * @return 分页信息
     */
    List<WineBox> findAll();

    /**
     * 分页查询
     *
     * @return 分页信息
     */
    List<WineBox> findAllNotBox();

    /**
     * 分页查询
     *
     * @return 分页信息
     */
    List<WineBox> getAllByRecommend();

    /**
     * 分页查询
     *
     * @param materialId 传入材料id
     * @return 分页信息
     */
    List<WineBox> findAllNotBoxAndMaterialId(Integer materialId);

    /**
     * 查询级联的对象
     *
     * @param id 主键
     * @return 对象
     */
    WineBox findByIdWithPurpose(Integer id);

    /**
     * 查询级联的对象
     *
     * @param id 主键
     * @return 对象
     */
    WineBox findByIdWithMaterial(Integer id);

    /**
     * 查询级联的对象
     *
     * @param id 主键
     * @return 对象
     */
    WineBox findByIdWithBoxType(Integer id);

    /**
     * 查询单个对象
     *
     * @param id 主键
     */
    WineBox findById(Integer id);

    List<WineBox> findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer styleId, Integer purposeId, int length);

    List<WineBox> findByBoxTypeIdAndLengthGreaterThan(Integer boxTypeId, int length);

    /**
     * app父子查询
     *
     * @return 集合
     */
    List<WineBox> findAllApp();

    /**
     * @param id
     * @return
     */
    List<WineBox> findByIdAndSon(Integer id);

    List<WineBox> findByBoxTypeIdAndStyleIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer styleId, Integer purposeId, int length);

    List<WineBox> findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer styleId, int length);

    List<WineBox> findByBoxTypeIdAndMaterialIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer purposeId, int length);

    List<WineBox> findByBoxTypeIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer styleId, int length);

    List<WineBox> findByBoxTypeIdAndMaterialIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, int length);

    List<WineBox> findByBoxTypeIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer purposeId, int length);
}
