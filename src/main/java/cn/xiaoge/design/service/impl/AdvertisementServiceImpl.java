package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.Advertisement;
import cn.xiaoge.design.entity.Material;
import cn.xiaoge.design.repository.AdvertisementRepository;
import cn.xiaoge.design.service.AdvertisementService;
import cn.xiaoge.design.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {


    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public void add(Advertisement advertisement) {
        advertisementRepository.save(advertisement);
    }


}
