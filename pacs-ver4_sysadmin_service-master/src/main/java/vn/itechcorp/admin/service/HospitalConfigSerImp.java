package vn.itechcorp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.itechcorp.base.exception.APIException;
import vn.com.itechcorp.base.service.dto.BaseDtoCreate;
import vn.com.itechcorp.base.service.dto.DtoUpdate;
import vn.com.itechcorp.base.service.impl.BaseDtoJpaServiceImpl;

import vn.itechcorp.admin.service.dto.ConfigAttributeDTOGet;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOCreate;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOGet;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;
import vn.itechcorp.admin.jpa.HospitalConfigRepo;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOUpdate;

import java.util.List;

@Service("HospitalConfigSerImp")
public class HospitalConfigSerImp extends BaseDtoJpaServiceImpl<HospitalConfigDTOGet, HospitalConfig, Long> implements HospitalConfigService {

    private final HospitalConfigRepo hospitalConfigRepo;
    @Autowired
    private final ConfigAttributeService configAttributeService;

    public HospitalConfigSerImp(HospitalConfigRepo hospitalConfigRepo, ConfigAttributeServiceImpl configAttributeService) {
        this.hospitalConfigRepo = hospitalConfigRepo;
        this.configAttributeService = configAttributeService;
    }

    @Override
    public HospitalConfigRepo getRepository() {
        return hospitalConfigRepo;
    }

    @Override
    public int findNumberAllByAttributeId(String attributeId) {
        List<HospitalConfig> list = hospitalConfigRepo.findAll();
        int count = 0;
        for (HospitalConfig item : list) {
            if (item.getAttributeId() != null && item.getAttributeId().equals(attributeId)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public HospitalConfigDTOGet convert(HospitalConfig object) {
        return new HospitalConfigDTOGet(object);
    }

    @Override
    public HospitalConfigDTOGet updateAndGet(DtoUpdate<HospitalConfig, Long> entity) throws APIException {
        return super.updateAndGet(entity);
    }

    @Override
    protected HospitalConfig createEntry(BaseDtoCreate<HospitalConfig, Long> entity) throws APIException {
        return super.createEntry(entity);
    }

    @Override
    protected HospitalConfig updateEntry(DtoUpdate<HospitalConfig, Long> entity) throws APIException {
        return super.updateEntry(entity);
    }

    @Override
    public void deleteByID(Long id) throws APIException {
        super.deleteByID(id);
    }

    @Override
    protected HospitalConfig validateAndUpdateEntry(DtoUpdate<HospitalConfig, Long> entity, HospitalConfig current) throws APIException {

        if (entity instanceof HospitalConfigDTOUpdate) {
            HospitalConfigDTOUpdate hospitalConfigDTOUpdate = (HospitalConfigDTOUpdate) entity;
            if (hospitalConfigDTOUpdate.getAttributeId() == null) {
                hospitalConfigDTOUpdate.setAttributeId(current.getAttributeId());
            }
            if (hospitalConfigDTOUpdate.getAttributeValue() == null) {
                hospitalConfigDTOUpdate.setAttributeValue(current.getAttributeValue());
            }
            if (validate(hospitalConfigDTOUpdate.converToHospitalConfig(), ((HospitalConfigDTOUpdate) entity).getAttributeId().equals(current.getAttributeId()))) {
                return super.validateAndUpdateEntry(entity, current);
            }

        }
        throw new IllegalArgumentException("Vuot qua so luong ConfigAttribute");
    }


    @Override
    protected HospitalConfig validateAndCreateEntry(BaseDtoCreate<HospitalConfig, Long> entity) throws APIException {
        if (entity instanceof HospitalConfigDTOCreate) {
            if (validate(entity.toEntry(), false)) {
                return super.validateAndCreateEntry(entity);
            }
        }
        throw new IllegalArgumentException("Vuot qua so luong ConfigAttribute");
    }

    @Override
    public HospitalConfigDTOGet deleteAndGet(Long id) throws APIException {
        return super.deleteAndGet(id);
    }

    @Override

    public HospitalConfigDTOGet getById(Long id) throws APIException {
        return super.getById(id);
    }

    public boolean validate(HospitalConfig hospitalConfig, boolean noUpdateAttribute) {
        if (hospitalConfig == null) {
            return false;
        }
        String attributeId;
        attributeId = hospitalConfig.getAttributeId();
        String attributeValue = hospitalConfig.getAttributeValue();
        ConfigAttributeDTOGet configAttribute;
        configAttribute = configAttributeService.getById(attributeId);
        String type = configAttribute.getDatatype();
        if (type != null) {
            if (type.equals("BOOLEAN")) {
                if (!(attributeValue.equals("true") || attributeValue.equals("false"))) {
                    throw new IllegalArgumentException("ConfigAttribute co datatype la BOOLEAN nen attributeValue phai la true/false");
                }

            } else if (type.equals("INTEGER")) {
                try {
                    Integer.parseInt(attributeValue);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("ConfigAttribute co datatype la INTEGER nen attributeValue phai la dang so nguyen");
                }
            } else if (type.equals("FLOAT")) {
                try {
                    Double.parseDouble(attributeValue);
                } catch (NumberFormatException e) {
                    System.err.println("attributeValue phai la dang so thuc");
                    throw new NumberFormatException("ConfigAttribute co datatype la FLOAT nen attributeValue phai la dang so thuc");
                }

            }
        }
        attributeId = hospitalConfig.getAttributeId();
        Integer maxOccurs = configAttribute.getMaxOccurs();;
        if (noUpdateAttribute) {
           return true;
        }

        if (this.findNumberAllByAttributeId(attributeId) >= maxOccurs) {
            return false;
        }
        return true;
    }

}
