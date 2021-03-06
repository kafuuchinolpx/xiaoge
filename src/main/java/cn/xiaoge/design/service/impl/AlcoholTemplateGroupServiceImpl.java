package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.AlcoholTemplate;
import cn.xiaoge.design.entity.AlcoholTemplateGroup;
import cn.xiaoge.design.repository.AlcoholTemplateGroupRepository;
import cn.xiaoge.design.service.AlcoholTemplateGroupService;
import cn.xiaoge.design.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlcoholTemplateGroupServiceImpl implements AlcoholTemplateGroupService {


    @Autowired
    private AlcoholTemplateGroupRepository alcoholTemplateGroupRepository;


    @Override
    public void add(AlcoholTemplateGroup alcoholTemplateGroup) {
        alcoholTemplateGroupRepository.save(alcoholTemplateGroup);
    }


    @Override
    public void deleteById(Integer id) {
        alcoholTemplateGroupRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(AlcoholTemplateGroup alcoholTemplateGroup) {
        System.out.println(alcoholTemplateGroup);
        Optional<AlcoholTemplateGroup> byId = alcoholTemplateGroupRepository.findById(alcoholTemplateGroup.getId());
        if (byId.isPresent()) {
            AlcoholTemplateGroup obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(alcoholTemplateGroup, obj);
            alcoholTemplateGroupRepository.save(obj);
        }
    }


    @Override
    public List<AlcoholTemplateGroup> findAll() {
        return alcoholTemplateGroupRepository.findAll();
    }


    @Override
    public AlcoholTemplateGroup findById(Integer id) {
        Optional<AlcoholTemplateGroup> byId = alcoholTemplateGroupRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

}
