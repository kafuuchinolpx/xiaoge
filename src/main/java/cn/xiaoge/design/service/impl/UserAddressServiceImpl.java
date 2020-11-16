package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.AppUser;
import cn.xiaoge.design.entity.UserAddress;
import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.repository.UserAddressRepository;
import cn.xiaoge.design.service.UserAddressService;
import cn.xiaoge.design.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressServiceImpl implements UserAddressService {


    @Autowired
    private UserAddressRepository userAddressRepository;


    @Override
    public void add(UserAddress userAddress) {
        userAddressRepository.save(userAddress);
    }


    @Override
    public void deleteById(Integer id) {
        userAddressRepository.deleteById(id);
    }

    @Override
    public List<UserAddress> findByUserId(Integer id) {
        List<UserAddress> byUserId = userAddressRepository.findByUserId(id);
        if (0 == byUserId.size()) {
            return null;
        }
        return byUserId;

    }


}
