package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.entity.QuotationInformation;
import cn.xiaoge.design.entity.vo.PageBean;
import cn.xiaoge.design.repository.QuotationInformationRepository;
import cn.xiaoge.design.service.QuotationInformationService;
import cn.xiaoge.design.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;


@Service
public class QuotationInformationServiceImpl implements QuotationInformationService {


    @Autowired
    private QuotationInformationRepository quotationInformationRepository;


    @Override
    public void add(QuotationInformation quotationInformation) {
        quotationInformationRepository.save(quotationInformation);
    }


    @Override
    public void deleteById(Integer id) {
        quotationInformationRepository.deleteById(id);
    }


    @Override
    public void updateNotNull(QuotationInformation quotationInformation) {
        Optional<QuotationInformation> byId = quotationInformationRepository.findById(quotationInformation.getId());
        if (byId.isPresent()) {
            QuotationInformation obj = byId.get();
            BeanUtil.copyPropertiesIgnoreNull(quotationInformation, obj);
            quotationInformationRepository.save(obj);
        }
    }


    @Override
    public PageBean<QuotationInformation> findAll(Integer page, String searchKey, String order, Integer size) {
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
            return PageBean.of(quotationInformationRepository.findAll(pageable), order, searchKey);
        } else {
            return PageBean.of(quotationInformationRepository.findByNameLike("%" + searchKey + "%", pageable), order, searchKey);
        }
    }


    @Override
    public QuotationInformation findById(Integer id) {
        Optional<QuotationInformation> byId = quotationInformationRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }


}
