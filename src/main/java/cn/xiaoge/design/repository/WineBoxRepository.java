package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.WineBox;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WineBoxRepository extends JpaRepository<WineBox, Integer> {

    WineBox findAllById(Integer id);



}
