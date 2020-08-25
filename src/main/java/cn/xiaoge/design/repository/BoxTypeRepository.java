package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.BoxType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoxTypeRepository extends JpaRepository<BoxType, Integer>  {
    Page<BoxType> findByNameLike(String name, Pageable pageable);

    List<BoxType> findByNameLike(String name);



}
