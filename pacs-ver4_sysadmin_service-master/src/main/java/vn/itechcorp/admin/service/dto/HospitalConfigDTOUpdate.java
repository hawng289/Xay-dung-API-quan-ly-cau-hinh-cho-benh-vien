package vn.itechcorp.admin.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoUpdate;

import vn.itechcorp.admin.service.ConfigAttributeService;

import vn.itechcorp.admin.jpa.entity.HospitalConfig;
import vn.itechcorp.admin.service.ConfigAttributeServiceImpl;
import vn.itechcorp.admin.service.HospitalConfigService;

import javax.persistence.Column;
@NoArgsConstructor
@Getter
@Setter
public class HospitalConfigDTOUpdate extends DtoUpdate<HospitalConfig, Long> {
    private String attributeId;

    private String attributeValue;
    private static ConfigAttributeServiceImpl configAttributeService = null;

    private Boolean preferred;

    private String hospitalId;

    public HospitalConfigDTOUpdate(HospitalConfig hospitalConfig) {
        super.setId(hospitalConfig.getId());
        attributeValue = hospitalConfig.getAttributeValue();
        preferred = hospitalConfig.getPreferred();
        attributeId = hospitalConfig.getAttributeId();
        hospitalId = hospitalConfig.getHospitalId();
    }

    public HospitalConfigDTOUpdate(Long id) {
        super.setId(id);
    }

    public HospitalConfig converToHospitalConfig () {
        return new HospitalConfig(getId(),attributeId,  attributeValue.trim().toLowerCase(), preferred, hospitalId);
    }

    @Override
    public boolean apply(HospitalConfig hospitalConfig) {

        // Check null
        if (hospitalId != null) {
            hospitalConfig.setHospitalId(hospitalId);
        } else {
            return  false;
        }

        if (preferred != null) {
            preferred = true;
            hospitalConfig.setPreferred(preferred);
        }

        if (attributeValue != null) {
            hospitalConfig.setAttributeValue(attributeValue);
        }
        if (attributeId != null) {
            hospitalConfig.setAttributeId(attributeId);
        }
        return true;

    }

}
