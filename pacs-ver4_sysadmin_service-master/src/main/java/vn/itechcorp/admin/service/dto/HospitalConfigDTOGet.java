package vn.itechcorp.admin.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoGet;
import vn.itechcorp.admin.jpa.entity.ConfigAttribute;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;
@Setter
@Getter
@NoArgsConstructor
public class HospitalConfigDTOGet extends DtoGet<HospitalConfig, Long> {
    private String attributeId;

    private String attributeValue;

    private  Boolean preferred;

    private  String hospitalId;


    public HospitalConfigDTOGet(HospitalConfig object) {
        super(object);
        System.out.println(this);
    }

    @Override
    public void parse(HospitalConfig object) {
        this.attributeId = object.getAttributeId();
        this.attributeValue = object.getAttributeValue();
        this.preferred = object.getPreferred();
        this.hospitalId = object.getHospitalId();
    }
}


