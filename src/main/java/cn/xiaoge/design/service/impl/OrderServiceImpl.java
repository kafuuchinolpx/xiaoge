package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.service.OrderService;
import cn.xiaoge.design.entity.Order;
import cn.xiaoge.design.repository.OrderRepository;
import cn.xiaoge.design.util.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.xiaoge.design.entity.AppUser;
import cn.xiaoge.design.repository.AppUserRepository;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void add(Order order) {
        orderRepository.save(order);
    }


    @Override
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(Order order) {
        Optional<Order> byId = orderRepository.findById(order.getId());
        if (byId.isPresent()) {
            Order obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(order, obj);
            orderRepository.save(obj);
        }
    }


    @Override
    public PageBean<Order> findAll(Integer page, String order, Integer size) {
        Pageable pageable;
        if (!StringUtils.isEmpty(order)) {
            String[] s = order.split(" ");
            if (s.length == 2) {
                Sort sort = new Sort(Sort.Direction.fromString(s[1]), s[0]);
                pageable = PageRequest.of(page - 1, size, sort);
            } else {
                pageable = PageRequest.of(page - 1, size);
            }
        } else {
            pageable = PageRequest.of(page - 1, size);
        }
        return PageBean.of(orderRepository.findAll(pageable), order, "");
    }




    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public Order findByIdWithAppUser(Integer id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            Order order = byId.get();
            Integer userId = order.getUserId();
            if (userId!=null) {
                Optional<AppUser> optionalAppUser = appUserRepository.findById(userId);
                if (optionalAppUser.isPresent()){
                    order.setAppUser(optionalAppUser.get());
                }
            }
            return order;
        }
        return null;
    }



    @Override
    public Order findById(Integer id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }


}
