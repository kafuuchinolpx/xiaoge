package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.QiqiTestPages;
import cn.xiaoge.design.repository.QiqiTestPagesRepository;
import cn.xiaoge.design.service.QiqiTestPagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QiqiTestPagesServiceImpl implements QiqiTestPagesService {


    @Autowired
    private QiqiTestPagesRepository qiqiTestPagesRepository;

    @Override
    public List<QiqiTestPages> findAll() {
        return qiqiTestPagesRepository.findAll();
    }

}
