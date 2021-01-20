package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.QiqiTest;
import cn.xiaoge.design.entity.vo.PageBean;

import java.util.List;


/**
 * 查询所有
 */
public interface QiqiTestService {
    List<QiqiTest> findAll();

    QiqiTest findById(Integer id);
}
