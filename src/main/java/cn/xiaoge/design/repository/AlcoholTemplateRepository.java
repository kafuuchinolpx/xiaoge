package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.AlcoholTemplate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AlcoholTemplateRepository extends JpaRepository<AlcoholTemplate, Integer>  {



    List<AlcoholTemplate> findByBoxTypeIdAndMaterialIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer purposeId, int length);
}
