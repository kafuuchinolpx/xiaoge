package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.BoxType;
import cn.xiaoge.design.entity.Material;
import cn.xiaoge.design.entity.Purpose;
import cn.xiaoge.design.entity.WineBox;
import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.repository.BoxTypeRepository;
import cn.xiaoge.design.repository.MaterialRepository;
import cn.xiaoge.design.repository.PurposeRepository;
import cn.xiaoge.design.repository.WineBoxRepository;
import cn.xiaoge.design.service.WineBoxService;
import cn.xiaoge.design.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WineBoxServiceImpl implements WineBoxService {


    @Autowired
    private WineBoxRepository wineBoxRepository;


    @Override
    public void add(WineBox wineBox) {
        wineBoxRepository.save(wineBox);
    }


    @Override
    public void deleteById(Integer id) {
        wineBoxRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(WineBox wineBox) {
        System.out.println(wineBox);
        Optional<WineBox> byId = wineBoxRepository.findById(wineBox.getId());
        if (byId.isPresent()) {
            WineBox obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(wineBox, obj);
            wineBoxRepository.save(obj);
        }
    }

    @Override
    public PageBean<WineBox> findAll(Integer page, String order, Integer size) {
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
        return PageBean.of(wineBoxRepository.findAllByDeleteState(pageable, 1), order, "");
    }

    @Override
    public List<WineBox> findAll() {
        return wineBoxRepository.findAllByDeleteState(0);
    }

    @Override
    public List<WineBox> findAllNotBox() {
        return wineBoxRepository.findAllByDeleteState(1);
    }

    @Override
    public List<WineBox> getAllByRecommend() {
        return wineBoxRepository.getAllByRecommend(1);
    }

    @Override
    public List<WineBox> findAllNotBoxAndMaterialId(Integer materialId) {
        if (materialId == 0) {
            return wineBoxRepository.findAllByDeleteState(1);
        }
        return wineBoxRepository.findAllByDeleteStateAndMaterialId(1, materialId);
    }

    @Autowired
    private PurposeRepository purposeRepository;

    @Override
    public WineBox findByIdWithPurpose(Integer id) {
        Optional<WineBox> byId = wineBoxRepository.findById(id);
        if (byId.isPresent()) {
            WineBox wineBox = byId.get();
            Integer purposeId = wineBox.getPurposeId();
            if (purposeId != null) {
                Optional<Purpose> optionalPurpose = purposeRepository.findById(purposeId);
                if (optionalPurpose.isPresent()) {
                    wineBox.setPurpose(optionalPurpose.get());
                }
            }
            return wineBox;
        }
        return null;
    }


    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public WineBox findByIdWithMaterial(Integer id) {
        Optional<WineBox> byId = wineBoxRepository.findById(id);
        if (byId.isPresent()) {
            WineBox wineBox = byId.get();
            Integer materialId = wineBox.getMaterialId();
            if (materialId != null) {
                Optional<Material> optionalMaterial = materialRepository.findById(materialId);
                if (optionalMaterial.isPresent()) {
                    wineBox.setMaterial(optionalMaterial.get());
                }
            }
            return wineBox;
        }
        return null;
    }

    @Autowired
    private BoxTypeRepository boxTypeRepository;

    @Override
    public WineBox findByIdWithBoxType(Integer id) {
        Optional<WineBox> byId = wineBoxRepository.findById(id);
        if (byId.isPresent()) {
            WineBox wineBox = byId.get();
            Integer boxTypeId = wineBox.getBoxTypeId();
            if (boxTypeId != null) {
                Optional<BoxType> optionalBoxType = boxTypeRepository.findById(boxTypeId);
                if (optionalBoxType.isPresent()) {
                    wineBox.setBoxType(optionalBoxType.get());
                }
            }
            return wineBox;
        }
        return null;
    }

    @Override
    public WineBox findById(Integer id) {
        Optional<WineBox> byId = wineBoxRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }


    @Override
    public List<WineBox> findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer styleId, Integer purposeId, int length) {
        List<WineBox> byBoxTypeIdAndMaterialIdAndStyleIdAndLength = wineBoxRepository.findAllByBoxTypeIdAndMaterialIdAndStyleIdAndPurposeIdAndGroupIdAndLengthAndDeleteState(boxTypeId, materialId, styleId, purposeId, 0, length, 1);
        byBoxTypeIdAndMaterialIdAndStyleIdAndLength.forEach(o -> o.setSon(wineBoxRepository.findByGroupIdAndLength(o.getId(), length)));
        return byBoxTypeIdAndMaterialIdAndStyleIdAndLength;
    }

    @Override
    public List<WineBox> findByBoxTypeIdAndLengthGreaterThan(Integer boxTypeId, int length) {
        List<WineBox> byBoxTypeIdAndLengthGreaterThan = wineBoxRepository.findAllByBoxTypeIdAndGroupIdAndLengthAndDeleteState(boxTypeId, 0, length, 1);
        byBoxTypeIdAndLengthGreaterThan.forEach(o -> o.setSon(wineBoxRepository.findByGroupIdAndLength(o.getId(), length)));
        return byBoxTypeIdAndLengthGreaterThan;
    }

    @Override
    public List<WineBox> findAllApp() {
        List<WineBox> wineBoxs = wineBoxRepository.findAllByGroupId(0);
        wineBoxs.forEach(o -> o.setSon(wineBoxRepository.findByGroupId(o.getId())));
        return wineBoxs;
    }

    @Override
    public List<WineBox> findByIdAndSon(Integer id) {
        List<WineBox> wineBox = new ArrayList();
        wineBox.add(wineBoxRepository.findAllById(id));
        List<WineBox> byGroupId = wineBoxRepository.findByGroupId(id);
        byGroupId.forEach(o -> wineBox.add(o));
        return wineBox;
    }

    @Override
    public List<WineBox> findByBoxTypeIdAndStyleIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer styleId, Integer purposeId, int length) {
        List<WineBox> byBoxTypeIdAndMaterialIdAndStyleIdAndLength = wineBoxRepository.findAllByBoxTypeIdAndStyleIdAndPurposeIdAndGroupIdAndLengthAndDeleteState(boxTypeId, styleId, purposeId, 0, length, 1);
        byBoxTypeIdAndMaterialIdAndStyleIdAndLength.forEach(o -> o.setSon(wineBoxRepository.findByGroupIdAndLength(o.getId(), length)));
        return byBoxTypeIdAndMaterialIdAndStyleIdAndLength;
    }

    @Override
    public List<WineBox> findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer styleId, int length) {
        List<WineBox> byBoxTypeIdAndMaterialIdAndStyleIdAndLength = wineBoxRepository.findAllByBoxTypeIdAndMaterialIdAndStyleIdAndGroupIdAndLengthAndDeleteState(boxTypeId, materialId, styleId, 0, length, 1);
        byBoxTypeIdAndMaterialIdAndStyleIdAndLength.forEach(o -> o.setSon(wineBoxRepository.findByGroupIdAndLength(o.getId(), length)));
        return byBoxTypeIdAndMaterialIdAndStyleIdAndLength;
    }

    @Override
    public List<WineBox> findByBoxTypeIdAndMaterialIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, Integer purposeId, int length) {
        List<WineBox> byBoxTypeIdAndMaterialIdAndStyleIdAndLength = wineBoxRepository.findAllByBoxTypeIdAndMaterialIdAndPurposeIdAndGroupIdAndLengthAndDeleteState(boxTypeId, materialId, purposeId, 0, length, 1);
        byBoxTypeIdAndMaterialIdAndStyleIdAndLength.forEach(o -> o.setSon(wineBoxRepository.findByGroupIdAndLength(o.getId(), length)));
        return byBoxTypeIdAndMaterialIdAndStyleIdAndLength;
    }

    @Override
    public List<WineBox> findByBoxTypeIdAndStyleIdAndLengthGreaterThan(Integer boxTypeId, Integer styleId, int length) {
        List<WineBox> byBoxTypeIdAndMaterialIdAndStyleIdAndLength = wineBoxRepository.findAllByBoxTypeIdAndStyleIdAndGroupIdAndLengthAndDeleteState(boxTypeId, styleId, 0, length, 1);
        byBoxTypeIdAndMaterialIdAndStyleIdAndLength.forEach(o -> o.setSon(wineBoxRepository.findByGroupIdAndLength(o.getId(), length)));
        return byBoxTypeIdAndMaterialIdAndStyleIdAndLength;
    }

    @Override
    public List<WineBox> findByBoxTypeIdAndMaterialIdAndLengthGreaterThan(Integer boxTypeId, Integer materialId, int length) {
        List<WineBox> byBoxTypeIdAndMaterialIdAndStyleIdAndLength = wineBoxRepository.findAllByBoxTypeIdAndMaterialIdAndGroupIdAndLengthAndDeleteState(boxTypeId, materialId, 0, length, 1);
        byBoxTypeIdAndMaterialIdAndStyleIdAndLength.forEach(o -> o.setSon(wineBoxRepository.findByGroupIdAndLength(o.getId(), length)));
        return byBoxTypeIdAndMaterialIdAndStyleIdAndLength;
    }

    @Override
    public List<WineBox> findByBoxTypeIdAndPurposeIdAndLengthGreaterThan(Integer boxTypeId, Integer purposeId, int length) {
        List<WineBox> byBoxTypeIdAndMaterialIdAndStyleIdAndLength = wineBoxRepository.findAllByBoxTypeIdAndPurposeIdAndGroupIdAndLengthAndDeleteState(boxTypeId, purposeId, 0, length, 1);
        byBoxTypeIdAndMaterialIdAndStyleIdAndLength.forEach(o -> o.setSon(wineBoxRepository.findByGroupIdAndLength(o.getId(), length)));
        return byBoxTypeIdAndMaterialIdAndStyleIdAndLength;
    }
}
