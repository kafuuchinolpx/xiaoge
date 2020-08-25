package cn.xiaoge.design.repository;

import cn.xiaoge.design.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderRepository extends JpaRepository<Order, Integer>  {



}
