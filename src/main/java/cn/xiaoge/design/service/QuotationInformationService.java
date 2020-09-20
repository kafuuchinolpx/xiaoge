package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.QuotationInformation;
import cn.xiaoge.design.entity.vo.PageBean;

import java.util.List;


public interface QuotationInformationService {

    /**
     * 添加报价
     *
     * @param quotationInformation 价格
     */
    void add(QuotationInformation quotationInformation);

    /**
     * 删除报价
     *
     * @param id 报价id
     */
    void deleteById(Integer id);

    /**
     * 修改不为空的属性
     *
     * @param quotationInformation 报价
     */
    void updateNotNull(QuotationInformation quotationInformation);

    /**
     * 分页查询
     *
     * @param page      页数
     * @param size      页面大小
     * @param order     排序
     * @param searchKey 搜索的属性
     * @return 分页信息
     */
    PageBean<QuotationInformation> findAll(Integer page, String searchKey, String order, Integer size);


    /**
     * 查询单个对象
     *
     * @param id 主键
     */
    QuotationInformation findById(Integer id);

    List<QuotationInformation> findAllApp();
}
