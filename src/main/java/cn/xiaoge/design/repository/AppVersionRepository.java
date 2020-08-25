package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.AppVersion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AppVersionRepository extends JpaRepository<AppVersion, Integer>  {
    Page<AppVersion> findByVersionNameLike(String versionName, Pageable pageable);

    List<AppVersion> findByVersionNameLike(String versionName);



}
