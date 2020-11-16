package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {


    List<UserAddress> findByUserId(Integer id);
}
