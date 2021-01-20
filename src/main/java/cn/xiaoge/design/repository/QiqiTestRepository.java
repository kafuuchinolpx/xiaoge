package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.QiqiTest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QiqiTestRepository extends JpaRepository<QiqiTest, Integer> {

    QiqiTest findAllById(Integer id);
}
