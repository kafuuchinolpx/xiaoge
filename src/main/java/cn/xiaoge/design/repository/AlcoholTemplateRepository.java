package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.AlcoholTemplate;

import java.util.List;

import cn.xiaoge.design.entity.AlcoholTemplateGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AlcoholTemplateRepository extends JpaRepository<AlcoholTemplate, Integer> {


    List<AlcoholTemplate> findByBoxTypeIdAndMaterialIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer purposeId, int length);

    List<AlcoholTemplate> findByBoxTypeIdAndMaterialIdAndStyleIdAndLength(Integer boxTypeId, Integer materialId, Integer styleId, int length);

    List<AlcoholTemplate> findByBoxTypeIdAndLengthGreaterThan(Integer boxTypeId, int length);

    List<AlcoholTemplate> findAllByGroupId(int i);


    List<AlcoholTemplate> findByGroupId(int i);
}
