package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.WineBox;
import cn.xiaoge.design.repository.WineBoxRepository;
import cn.xiaoge.design.service.WineBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Winebox)表服务实现类
 *
 * @author makejava
 * @since 2020-11-21 15:45:35
 */
@Service
public class WineBoxServiceImpl implements WineBoxService {
    @Autowired
    WineBoxRepository wineBoxRepository;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WineBox findAllById(Integer id) {
        return this.wineBoxRepository.findAllById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<WineBox> findAll() {
        return wineBoxRepository.findAll();
    }

    /**
     * 新增数据
     *
     * @param winebox 实例对象
     * @return 实例对象
     */
    @Override
    public WineBox insert(WineBox winebox) {
        this.wineBoxRepository.save(winebox);
        return winebox;
    }

    @Override
    public WineBox update(WineBox winebox) {
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public void deleteById(Integer id) {
        wineBoxRepository.deleteById(id);
    }
}