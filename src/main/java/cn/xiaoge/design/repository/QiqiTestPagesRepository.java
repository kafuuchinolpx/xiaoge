package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.QiqiTestPages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QiqiTestPagesRepository extends JpaRepository<QiqiTestPages, Integer> {


    List<QiqiTestPages> findAllById(Integer pages);

}
