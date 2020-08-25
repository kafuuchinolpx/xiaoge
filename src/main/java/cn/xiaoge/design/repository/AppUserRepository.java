package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.AppUser;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AppUserRepository extends JpaRepository<AppUser, Integer>  {


    Optional<AppUser> findByTelephone(String telephone);
}
