package vn.itechcorp.admin.service.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoCreate;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;
import vn.itechcorp.admin.service.ConfigAttributeServiceImpl;

@Getter
@Setter
public class HospitalConfigDTOCreate extends DtoCreate<HospitalConfig, Long> {

    private String attributeId;

    @NonNull
    private String attributeValue;

    private  Boolean preferred;

    @NonNull
    private  String hospitalId;

    public HospitalConfigDTOCreate(Long id, String attributeId, @NonNull String attributeValue, Boolean preferred, @NonNull String hospitalId) {
        setId(id);
        this.attributeId = attributeId;
        this.attributeValue = attributeValue;
        this.preferred = preferred;
        this.hospitalId = hospitalId;
    }

    @Override
    public HospitalConfig toEntry() {
        if (preferred == null) {
            preferred = true;
        }


        attributeValue = attributeValue.trim().toLowerCase();

        HospitalConfig object = new HospitalConfig();
        object.setId(getId());
        object.setAttributeId(attributeId);
        object.setPreferred(preferred);
        object.setHospitalId(hospitalId);
        object.setAttributeValue(attributeValue);

        return object;
    }
}
