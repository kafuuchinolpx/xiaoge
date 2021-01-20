package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.QiqiTest;
import cn.xiaoge.design.entity.QiqiTestPages;
import cn.xiaoge.design.repository.QiqiTestPagesRepository;
import cn.xiaoge.design.repository.QiqiTestRepository;
import cn.xiaoge.design.service.QiqiTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QiqiTestServiceImpl implements QiqiTestService {


    @Autowired
    private QiqiTestRepository qiqiTestRepository;

    @Autowired
    QiqiTestPagesRepository qiqiTestPagesRepository;

    @Override
    public List<QiqiTest> findAll() {
        List<QiqiTest> all = qiqiTestRepository.findAll();
        for (QiqiTest qiqiTest1 : all) {
            List<QiqiTestPages> qiqiTestPages = qiqiTestPagesRepository.findAllById(qiqiTest1.getPagesId());
            qiqiTest1.setPages(qiqiTestPages);
        }
        return all;
    }

    @Override
    public QiqiTest findById(Integer id) {
        QiqiTest allById = qiqiTestRepository.findAllById(id);
        List<QiqiTestPages> allById1 = qiqiTestPagesRepository.findAllById(allById.getPagesId());
        allById.setPages(allById1);
        return allById;
    }
}
