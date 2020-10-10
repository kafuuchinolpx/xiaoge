package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.AlcoholTemplate;

import java.util.Collection;
import java.util.List;

import cn.xiaoge.design.entity.AlcoholTemplateGroup;
import cn.xiaoge.design.entity.vo.PageBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AlcoholTemplateRepository extends JpaRepository<AlcoholTemplate, Integer> {

    List<AlcoholTemplate> findAllByGroupId(int i);

    List<AlcoholTemplate> findByGroupId(int i);

    List<AlcoholTemplate> findByGroupIdAndLength(Integer id, int length);

    List<AlcoholTemplate> findAllByBoxTypeIdAndMaterialIdAndStyleIdAndPurposeIdAndGroupIdAndLength(Integer boxTypeId, Integer materialId, Integer styleId, Integer purposeId, int i, int length);

    List<AlcoholTemplate> findAllByBoxTypeIdAndGroupIdAndLength(Integer boxTypeId, int i, int length);

    List<AlcoholTemplate> findAllByDeleteState(int i);

    Page<AlcoholTemplate> findAllByDeleteState(Pageable var1, int i);
}
