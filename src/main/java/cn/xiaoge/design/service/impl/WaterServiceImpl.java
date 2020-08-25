package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.Water;
import cn.xiaoge.design.repository.WaterRepository;
import cn.xiaoge.design.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * (Water)表服务实现类
 *
 * @author makejava
 * @since 2020-08-25 16:42:11
 */
@Service("waterService")
public class WaterServiceImpl implements WaterService {
    @Autowired
    private WaterRepository waterRepository;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Water queryById(Integer id) {
        Optional<Water> byId = waterRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    /**
     * 新增数据
     *
     * @param water 实例对象
     * @return 实例对象
     */
    @Override
    public Water insert(Water water) {
        this.waterRepository.save(water);
        return water;
    }

    /**
     * 修改数据
     *
     * @param water 实例对象
     * @return 实例对象
     */
    @Override
    public Water update(Water water) {
        this.waterRepository.save(water);
        return this.queryById(water.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        waterRepository.deleteById(id);
        return true;
    }
}