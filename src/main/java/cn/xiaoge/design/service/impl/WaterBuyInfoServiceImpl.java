package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.AppUser;
import cn.xiaoge.design.entity.WaterBuyInfo;
import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.repository.AppUserRepository;
import cn.xiaoge.design.repository.WaterBuyInfoRepository;
import cn.xiaoge.design.service.WaterBuyInfoService;
import cn.xiaoge.design.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class WaterBuyInfoServiceImpl implements WaterBuyInfoService {


    @Autowired
    private WaterBuyInfoRepository waterBuyInfoRepository;


    @Override
    public void add(WaterBuyInfo waterBuyInfo) {
        waterBuyInfoRepository.save(waterBuyInfo);
    }


    @Override
    public void deleteById(Integer id) {
        waterBuyInfoRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(WaterBuyInfo waterBuyInfo) {
        Optional<WaterBuyInfo> byId = waterBuyInfoRepository.findById(waterBuyInfo.getId());
        if (byId.isPresent()) {
            WaterBuyInfo obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(waterBuyInfo, obj);
            waterBuyInfoRepository.save(obj);
        }
    }


    @Override
    public PageBean<WaterBuyInfo> findAll(Integer page, String waterBuyInfo, Integer size) {
        Pageable pageable;
        if (!StringUtils.isEmpty(waterBuyInfo)) {
            String[] s = waterBuyInfo.split(" ");
            if (s.length == 2) {
                Sort sort = new Sort(Sort.Direction.fromString(s[1]), s[0]);
                pageable = PageRequest.of(page - 1, size, sort);
            } else {
                pageable = PageRequest.of(page - 1, size);
            }
        } else {
            pageable = PageRequest.of(page - 1, size);
        }
        return PageBean.of(waterBuyInfoRepository.findAll(pageable), waterBuyInfo, "");
    }


    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public WaterBuyInfo findByIdWithAppUser(Integer id) {
        Optional<WaterBuyInfo> byId = waterBuyInfoRepository.findById(id);
        if (byId.isPresent()) {
            WaterBuyInfo waterBuyInfo = byId.get();
            Integer userId = waterBuyInfo.getUserId();
            if (userId != null) {
                Optional<AppUser> optionalAppUser = appUserRepository.findById(userId);
//                if (optionalAppUser.isPresent()) {
//                    waterBuyInfo.setAppUser(optionalAppUser.get());
//                }
            }
            return waterBuyInfo;
        }
        return null;
    }


    @Override
    public WaterBuyInfo findById(Integer id) {
        Optional<WaterBuyInfo> byId = waterBuyInfoRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }


}
