package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.service.BoxTypeService;
import cn.xiaoge.design.entity.BoxType;
import cn.xiaoge.design.repository.BoxTypeRepository;
import cn.xiaoge.design.util.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoxTypeServiceImpl implements BoxTypeService {


    @Autowired
    private BoxTypeRepository boxTypeRepository;


    @Override
    public void add(BoxType boxType) {
        boxTypeRepository.save(boxType);
    }


    @Override
    public void deleteById(Integer id) {
        boxTypeRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(BoxType boxType) {
        Optional<BoxType> byId = boxTypeRepository.findById(boxType.getId());
        if (byId.isPresent()) {
            BoxType obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(boxType, obj);
            boxTypeRepository.save(obj);
        }
    }


    @Override
    public PageBean<BoxType> findAll(Integer page, String searchKey, String order, Integer size) {
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
            return PageBean.of(boxTypeRepository.findAll(pageable), order, searchKey);
        } else {
            return PageBean.of(boxTypeRepository.findByNameLike("%" + searchKey + "%", pageable), order, searchKey);
        }
    }





    @Override
    public BoxType findById(Integer id) {
        Optional<BoxType> byId = boxTypeRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }


}
