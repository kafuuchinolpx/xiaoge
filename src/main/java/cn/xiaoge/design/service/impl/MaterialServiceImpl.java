package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.service.MaterialService;
import cn.xiaoge.design.entity.Material;
import cn.xiaoge.design.repository.MaterialRepository;
import cn.xiaoge.design.util.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements MaterialService {


    @Autowired
    private MaterialRepository materialRepository;


    @Override
    public void add(Material material) {
        materialRepository.save(material);
    }


    @Override
    public void deleteById(Integer id) {
        materialRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(Material material) {
        Optional<Material> byId = materialRepository.findById(material.getId());
        if (byId.isPresent()) {
            Material obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(material, obj);
            materialRepository.save(obj);
        }
    }


    @Override
    public PageBean<Material> findAll(Integer page, String searchKey, String order, Integer size) {
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
            return PageBean.of(materialRepository.findAll(pageable), order, searchKey);
        } else {
            return PageBean.of(materialRepository.findByNameLike("%" + searchKey + "%", pageable), order, searchKey);
        }
    }





    @Override
    public Material findById(Integer id) {
        Optional<Material> byId = materialRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }


}
