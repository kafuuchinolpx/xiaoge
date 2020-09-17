package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.AlcoholTemplate;

import java.util.List;

import cn.xiaoge.design.entity.AlcoholTemplateGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AlcoholTemplateRepository extends JpaRepository<AlcoholTemplate, Integer> {

    List<AlcoholTemplate> findAllByGroupId(int i);

    List<AlcoholTemplate> findByGroupId(int i);

    List<AlcoholTemplate> findAllByBoxTypeIdAndMaterialIdAndStyleIdAndGroupIdAndLength(Integer boxTypeId, Integer materialId, Integer styleId, int i, int length);

    List<AlcoholTemplate> findAllByBoxTypeIdAndGroupIdAndLengthGreaterThan(Integer boxTypeId, int i, int length);

    List<AlcoholTemplate> findByGroupIdAndLength(Integer id, int length);
}
