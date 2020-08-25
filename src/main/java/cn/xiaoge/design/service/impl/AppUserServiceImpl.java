package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.service.AppUserService;
import cn.xiaoge.design.entity.AppUser;
import cn.xiaoge.design.repository.AppUserRepository;
import cn.xiaoge.design.util.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {


    @Autowired
    private AppUserRepository appUserRepository;


    @Override
    public void add(AppUser appUser) {
        appUserRepository.save(appUser);
    }


    @Override
    public void deleteById(Integer id) {
        appUserRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(AppUser appUser) {
        Optional<AppUser> byId = appUserRepository.findById(appUser.getId());
        if (byId.isPresent()) {
            AppUser obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(appUser, obj);
            appUserRepository.save(obj);
        }
    }


    @Override
    public PageBean<AppUser> findAll(Integer page, String order, Integer size) {
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
        return PageBean.of(appUserRepository.findAll(pageable), order, "");
    }





    @Override
    public AppUser findById(Integer id) {
        Optional<AppUser> byId = appUserRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    @Override
    public AppUser findByTelephoneAndPassword(String telephone, String password) {
        Optional<AppUser> byId = appUserRepository.findByTelephone(telephone);
        if (byId.isPresent()) {
            AppUser user = byId.get();
            if (user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
