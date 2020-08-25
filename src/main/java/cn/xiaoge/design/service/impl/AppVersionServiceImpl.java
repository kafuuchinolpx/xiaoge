package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.service.AppVersionService;
import cn.xiaoge.design.entity.AppVersion;
import cn.xiaoge.design.repository.AppVersionRepository;
import cn.xiaoge.design.util.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppVersionServiceImpl implements AppVersionService {


    @Autowired
    private AppVersionRepository appVersionRepository;


    @Override
    public void add(AppVersion appVersion) {
        appVersionRepository.save(appVersion);
    }


    @Override
    public void deleteById(Integer id) {
        appVersionRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(AppVersion appVersion) {
        Optional<AppVersion> byId = appVersionRepository.findById(appVersion.getId());
        if (byId.isPresent()) {
            AppVersion obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(appVersion, obj);
            appVersionRepository.save(obj);
        }
    }


    @Override
    public PageBean<AppVersion> findAll(Integer page, String searchKey, String order, Integer size) {
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
            return PageBean.of(appVersionRepository.findAll(pageable), order, searchKey);
        } else {
            return PageBean.of(appVersionRepository.findByVersionNameLike("%" + searchKey + "%", pageable), order, searchKey);
        }
    }





    @Override
    public AppVersion findById(Integer id) {
        Optional<AppVersion> byId = appVersionRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }


}
