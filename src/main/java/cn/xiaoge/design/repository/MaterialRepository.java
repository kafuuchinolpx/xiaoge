package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.Material;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MaterialRepository extends JpaRepository<Material, Integer>  {
    Page<Material> findByNameLike(String name, Pageable pageable);

    List<Material> findByNameLike(String name);



}
