package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.WineBoxGroup;
import cn.xiaoge.design.repository.WineBoxGroupRepository;
import cn.xiaoge.design.service.WineBoxGroupService;
import cn.xiaoge.design.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WineBoxGroupServiceImpl implements WineBoxGroupService {


    @Autowired
    private WineBoxGroupRepository wineBoxGroupRepository;


    @Override
    public void add(WineBoxGroup wineBoxGroup) {
        wineBoxGroupRepository.save(wineBoxGroup);
    }


    @Override
    public void deleteById(Integer id) {
        wineBoxGroupRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(WineBoxGroup wineBoxGroup) {
        System.out.println(wineBoxGroup);
        Optional<WineBoxGroup> byId = wineBoxGroupRepository.findById(wineBoxGroup.getId());
        if (byId.isPresent()) {
            WineBoxGroup obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(wineBoxGroup, obj);
            wineBoxGroupRepository.save(obj);
        }
    }


    @Override
    public List<WineBoxGroup> findAll() {
        return wineBoxGroupRepository.findAll();
    }


    @Override
    public WineBoxGroup findById(Integer id) {
        Optional<WineBoxGroup> byId = wineBoxGroupRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

}
