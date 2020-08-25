package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.Purpose;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PurposeRepository extends JpaRepository<Purpose, Integer>  {
    Page<Purpose> findByNameLike(String name, Pageable pageable);

    List<Purpose> findByNameLike(String name);



}
