package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.service.AlcoholTemplateService;
import cn.xiaoge.design.entity.AlcoholTemplate;
import cn.xiaoge.design.repository.AlcoholTemplateRepository;
import cn.xiaoge.design.util.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.xiaoge.design.entity.Purpose;
import cn.xiaoge.design.repository.PurposeRepository;
import cn.xiaoge.design.entity.Material;
import cn.xiaoge.design.repository.MaterialRepository;
import cn.xiaoge.design.entity.BoxType;
import cn.xiaoge.design.repository.BoxTypeRepository;

@Service
public class AlcoholTemplateServiceImpl implements AlcoholTemplateService {


    @Autowired
    private AlcoholTemplateRepository alcoholTemplateRepository;


    @Override
    public void add(AlcoholTemplate alcoholTemplate) {
        alcoholTemplateRepository.save(alcoholTemplate);
    }


    @Override
    public void deleteById(Integer id) {
        alcoholTemplateRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(AlcoholTemplate alcoholTemplate) {
        System.out.println(alcoholTemplate);
        Optional<AlcoholTemplate> byId = alcoholTemplateRepository.findById(alcoholTemplate.getId());
        if (byId.isPresent()) {
            AlcoholTemplate obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(alcoholTemplate, obj);
            alcoholTemplateRepository.save(obj);
        }
    }


    @Override
    public PageBean<AlcoholTemplate> findAll(Integer page, String order, Integer size) {
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
        return PageBean.of(alcoholTemplateRepository.findAll(pageable), order, "");
    }




    @Autowired
    private PurposeRepository purposeRepository;

    @Override
    public AlcoholTemplate findByIdWithPurpose(Integer id) {
        Optional<AlcoholTemplate> byId = alcoholTemplateRepository.findById(id);
        if (byId.isPresent()) {
            AlcoholTemplate alcoholTemplate = byId.get();
            Integer purposeId = alcoholTemplate.getPurposeId();
            if (purposeId!=null) {
                Optional<Purpose> optionalPurpose = purposeRepository.findById(purposeId);
                if (optionalPurpose.isPresent()){
                    alcoholTemplate.setPurpose(optionalPurpose.get());
                }
            }
            return alcoholTemplate;
        }
        return null;
    }


    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public AlcoholTemplate findByIdWithMaterial(Integer id) {
        Optional<AlcoholTemplate> byId = alcoholTemplateRepository.findById(id);
        if (byId.isPresent()) {
            AlcoholTemplate alcoholTemplate = byId.get();
            Integer materialId = alcoholTemplate.getMaterialId();
            if (materialId!=null) {
                Optional<Material> optionalMaterial = materialRepository.findById(materialId);
                if (optionalMaterial.isPresent()){
                    alcoholTemplate.setMaterial(optionalMaterial.get());
                }
            }
            return alcoholTemplate;
        }
        return null;
    }

    @Autowired
    private BoxTypeRepository boxTypeRepository;

    @Override
    public AlcoholTemplate findByIdWithBoxType(Integer id) {
        Optional<AlcoholTemplate> byId = alcoholTemplateRepository.findById(id);
        if (byId.isPresent()) {
            AlcoholTemplate alcoholTemplate = byId.get();
            Integer boxTypeId = alcoholTemplate.getBoxTypeId();
            if (boxTypeId!=null) {
                Optional<BoxType> optionalBoxType = boxTypeRepository.findById(boxTypeId);
                if (optionalBoxType.isPresent()){
                    alcoholTemplate.setBoxType(optionalBoxType.get());
                }
            }
            return alcoholTemplate;
        }
        return null;
    }

    @Override
    public AlcoholTemplate findById(Integer id) {
        Optional<AlcoholTemplate> byId = alcoholTemplateRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    @Override
    public List<AlcoholTemplate> findByBoxTypeIdAndMaterialIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer purposeId, int length) {

        return  alcoholTemplateRepository.findByBoxTypeIdAndMaterialIdAndPurposeIdAndLengthGreaterThan(boxTypeId,materialId,purposeId,length);
    }

    @Override
    public List<AlcoholTemplate> findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer styleId, int length) {

        return  alcoholTemplateRepository.findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(boxTypeId,materialId,styleId,length);
    }
}
