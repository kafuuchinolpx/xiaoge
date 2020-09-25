package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.AlcoholTemplate;
import cn.xiaoge.design.entity.Water;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WaterRepository extends JpaRepository<Water, Integer> {

    List<Water> findByPurposeIdAndLength(Integer purposeId, int length);

    List<Water> findByPurposeId(Integer purposeId);

}
