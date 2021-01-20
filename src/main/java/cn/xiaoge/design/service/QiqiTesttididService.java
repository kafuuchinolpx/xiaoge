package cn.xiaoge.design.service;

import cn.xiaoge.design.entity.QiqiTesttidid;

import java.util.List;


/**
 * 查询所有
 */
public interface QiqiTesttididService {

    /**
     * 保存
     *
     * @return
     */
    List<QiqiTesttidid> findAll();

    /**
     * 保存
     *
     * @param qiqiTesttidid
     */
    void add(QiqiTesttidid qiqiTesttidid);
}
