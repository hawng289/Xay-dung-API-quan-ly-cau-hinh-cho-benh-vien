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

        if (hospitalId != null && !hospitalId.equals(hospitalConfig.getHospitalId())) {
            hospitalConfig.setHospitalId(hospitalId);
            return true;
        }

        if (preferred != null && !preferred.equals(hospitalConfig.getPreferred())) {
            hospitalConfig.setPreferred(preferred);
            return true;
        }

        if (attributeId != null && !attributeId.equals(hospitalConfig.getAttributeId())) {
            hospitalConfig.setAttributeId(attributeId);
            if (attributeValue != null && !attributeValue.equals(hospitalConfig.getAttributeValue())) {
                hospitalConfig.setAttributeValue(attributeValue);
            }
            return true;
        }

        if (attributeValue != null && !attributeValue.equals(hospitalConfig.getAttributeValue())) {
            hospitalConfig.setAttributeValue(attributeValue);
            return true;
        }



        return false;


    }

}
