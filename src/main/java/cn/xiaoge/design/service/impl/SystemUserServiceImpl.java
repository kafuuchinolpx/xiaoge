package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.service.SystemUserService;
import cn.xiaoge.design.entity.SystemUser;
import cn.xiaoge.design.repository.SystemUserRepository;
import cn.xiaoge.design.util.*;
import java.util.List;

import org.springframework.util.DigestUtils;
import cn.xiaoge.design.cacheAop.MyCacheable;
import cn.xiaoge.design.cacheAop.MyCacheEvict;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {


    @Autowired
    private SystemUserRepository systemUserRepository;


    @MyCacheEvict(allEntries = true)
    @Override
    public void add(SystemUser systemUser) {
        systemUserRepository.save(systemUser);
    }


    @MyCacheEvict(allEntries = true)
    @Override
    public void deleteById(Integer id) {
        systemUserRepository.deleteById(id);
    }


    @MyCacheEvict(allEntries = true)
    @Override
    public void updateNotNull(SystemUser systemUser) {
        Optional<SystemUser> byId = systemUserRepository.findById(systemUser.getId());
        if (byId.isPresent()) {
            SystemUser obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(systemUser, obj);
            systemUserRepository.save(obj);
        }
    }


    @MyCacheable
    @Override
    public PageBean<SystemUser> findAll(Integer page, String searchKey, String order, Integer size) {
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
        if (StringUtils.isEmpty(searchKey)) {
            return PageBean.of(systemUserRepository.findAll(pageable), order, searchKey);
        } else {
            return PageBean.of(systemUserRepository.findByNameLike("%" + searchKey + "%", pageable), order, searchKey);
        }
    }





    @MyCacheable
    @Override
    public SystemUser findById(Integer id) {
        Optional<SystemUser> byId = systemUserRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    @Override
    public SystemUser login(String loginName, String loginPassword) {
        List<SystemUser> usernameLike = systemUserRepository.findByName(loginName);
        String md5 = DigestUtils.md5DigestAsHex(loginPassword.getBytes());
        for (SystemUser user : usernameLike) {
            if (user.getPassword().equals(md5)){
                return user;
            }
        }
        return null;
    }

}
