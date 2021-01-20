package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.QiqiTesttidid;
import cn.xiaoge.design.repository.QiqiTesttididRepository;
import cn.xiaoge.design.service.QiqiTesttididService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QiqiTesttididServiceImpl implements QiqiTesttididService {

    @Autowired
    private QiqiTesttididRepository qiqiTesttididRepository;

    @Override
    public List<QiqiTesttidid> findAll() {
        return qiqiTesttididRepository.findAll();
    }

    @Override
    public void add(QiqiTesttidid qiqiTesttidid) {
        qiqiTesttididRepository.save(qiqiTesttidid);
    }
}
