package cn.xiaoge.design.repository;
import cn.xiaoge.design.entity.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface StyleRepository extends JpaRepository<Style, Integer>  {
    Page<Style> findByNameLike(String name, Pageable pageable);

    List<Style> findByNameLike(String name);



}
