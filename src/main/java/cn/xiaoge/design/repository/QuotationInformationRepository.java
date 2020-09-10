package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.QuotationInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuotationInformationRepository extends JpaRepository<QuotationInformation, Integer> {
    Page<QuotationInformation> findByNameLike(String name, Pageable pageable);

    List<QuotationInformation> findByNameLike(String name);


}
