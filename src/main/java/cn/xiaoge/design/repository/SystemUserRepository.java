package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.SystemUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SystemUserRepository extends JpaRepository<SystemUser, Integer>  {
    Page<SystemUser> findByNameLike(String name, Pageable pageable);

    List<SystemUser> findByNameLike(String name);


    List<SystemUser> findByName(String name);

}
