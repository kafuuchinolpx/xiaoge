package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.Style;
import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.repository.StyleRepository;
import cn.xiaoge.design.service.StyleService;
import cn.xiaoge.design.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class StyleServiceImpl implements StyleService {


    @Autowired
    private StyleRepository purposeRepository;


    @Override
    public void add(Style purpose) {
        purposeRepository.save(purpose);
    }


    @Override
    public void deleteById(Integer id) {
        purposeRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(Style purpose) {
        Optional<Style> byId = purposeRepository.findById(purpose.getId());
        if (byId.isPresent()) {
            Style obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(purpose, obj);
            purposeRepository.save(obj);
        }
    }


    @Override
    public PageBean<Style> findAll(Integer page, String searchKey, String order, Integer size) {
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
    public Style findById(Integer id) {
        Optional<Style> byId = purposeRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }


}
