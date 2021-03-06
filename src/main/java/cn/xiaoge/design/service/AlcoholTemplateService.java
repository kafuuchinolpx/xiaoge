package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.AlcoholTemplate;
import cn.xiaoge.design.entity.vo.PageBean;

import java.util.List;


public interface AlcoholTemplateService {

    /**
     * 添加酒模板
     *
     * @param alcoholTemplate 酒模板
     */
    void add(AlcoholTemplate alcoholTemplate);

    /**
     * 删除酒模板
     *
     * @param id 酒模板id
     */
    void deleteById(Integer id);

    /**
     * 修改不为空的属性
     *
     * @param alcoholTemplate 酒模板
     */
    void updateNotNull(AlcoholTemplate alcoholTemplate);

    /**
     * 分页查询
     *
     * @param page  页数
     * @param size  页面大小
     * @param order 排序
     * @return 分页信息
     */
    PageBean<AlcoholTemplate> findAll(Integer page, String order, Integer size);

    /**
     * 分页查询
     *
     * @return 分页信息
     */
    List<AlcoholTemplate> findAll();

    /**
     * 分页查询
     *
     * @return 分页信息
     */
    List<AlcoholTemplate> findAllNotBox();

    /**
     * 分页查询
     *
     * @return 分页信息
     */
    List<AlcoholTemplate> getAllByRecommend();

    /**
     * 分页查询
     *
     * @param materialId 传入材料id
     * @return 分页信息
     */
    List<AlcoholTemplate> findAllNotBoxAndMaterialId(Integer materialId);

    /**
     * 查询级联的对象
     *
     * @param id 主键
     * @return 对象
     */
    AlcoholTemplate findByIdWithPurpose(Integer id);

    /**
     * 查询级联的对象
     *
     * @param id 主键
     * @return 对象
     */
    AlcoholTemplate findByIdWithMaterial(Integer id);

    /**
     * 查询级联的对象
     *
     * @param id 主键
     * @return 对象
     */
    AlcoholTemplate findByIdWithBoxType(Integer id);

    /**
     * 查询单个对象
     *
     * @param id 主键
     */
    AlcoholTemplate findById(Integer id);

    List<AlcoholTemplate> findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer styleId, Integer purposeId, int length);

    List<AlcoholTemplate> findByBoxTypeIdAndLengthGreaterThan(Integer boxTypeId, int length);

    /**
     * app父子查询
     *
     * @return 集合
     */
    List<AlcoholTemplate> findAllApp();

    /**
     * @param id
     * @return
     */
    List<AlcoholTemplate> findByIdAndSon(Integer id);

    List<AlcoholTemplate> findByBoxTypeIdAndStyleIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer styleId, Integer purposeId, int length);

    List<AlcoholTemplate> findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer styleId, int length);

    List<AlcoholTemplate> findByBoxTypeIdAndMaterialIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer purposeId, int length);

    List<AlcoholTemplate> findByBoxTypeIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer styleId, int length);

    List<AlcoholTemplate> findByBoxTypeIdAndMaterialIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, int length);

    List<AlcoholTemplate> findByBoxTypeIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer purposeId, int length);
}
