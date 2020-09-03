package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.AlcoholTemplate;
import cn.xiaoge.design.entity.Water;
import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.repository.WaterRepository;
import cn.xiaoge.design.service.WaterService;
import cn.xiaoge.design.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public Object findAll(Integer page, String order, Integer size) {
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
        return PageBean.of(waterRepository.findAll(pageable), order, "");
    }

    @Override
    public Object findById(Integer id) {
        return waterRepository.findById(id);
    }

    @Override
    public void updateNotNull(Water water) {
        Optional<Water> byId = waterRepository.findById(water.getId());
        if (byId.isPresent()) {
            Water obj = byId.get();
            if (water.getHeaderStyle() != null) {
                obj.setHeaderStyle(water.getHeaderStyle());
            }
            if (water.getBodyStyle() != null) {
                obj.setBodyStyle(water.getBodyStyle());
            }
            System.out.println("*****************************" + obj);
            waterRepository.save(obj);
        }
    }

    @Override
    public Object findAll() {
        return waterRepository.findAll();
    }

    @Override
    public List<Water> findByPurposeIdAndLengthGreaterThan(Integer purposeId, int length) {
        return waterRepository.findByPurposeIdAndLengthGreaterThan(purposeId, length);
    }
}

