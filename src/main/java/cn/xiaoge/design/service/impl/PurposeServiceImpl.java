package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.service.PurposeService;
import cn.xiaoge.design.entity.Purpose;
import cn.xiaoge.design.repository.PurposeRepository;
import cn.xiaoge.design.util.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurposeServiceImpl implements PurposeService {


    @Autowired
    private PurposeRepository purposeRepository;


    @Override
    public void add(Purpose purpose) {
        purposeRepository.save(purpose);
    }


    @Override
    public void deleteById(Integer id) {
        purposeRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(Purpose purpose) {
        Optional<Purpose> byId = purposeRepository.findById(purpose.getId());
        if (byId.isPresent()) {
            Purpose obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(purpose, obj);
            purposeRepository.save(obj);
        }
    }


    @Override
    public PageBean<Purpose> findAll(Integer page, String searchKey, String order, Integer size) {
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
            return PageBean.of(purposeRepository.findAll(pageable), order, searchKey);
        } else {
            return PageBean.of(purposeRepository.findByNameLike("%" + searchKey + "%", pageable), order, searchKey);
        }
    }





    @Override
    public Purpose findById(Integer id) {
        Optional<Purpose> byId = purposeRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }


}
