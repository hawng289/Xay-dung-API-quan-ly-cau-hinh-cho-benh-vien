package com.example.demo.ris.dto;

import com.example.demo.ris.module.HospitalConfig;
import lombok.NonNull;
import vn.com.itechcorp.base.service.dto.DtoGet;
import vn.itechcorp.admin.jpa.entity.ConfigAttribute;

public class HospitalConfigDTOGet extends DtoGet<HospitalConfig, Long> {
    private String attributeId;

    @NonNull
    private String attributeValue;

    private  Boolean preferred;

    @NonNull
    private  String hospitalId;
    @Override
    public void parse(HospitalConfig hospitalConfig) {
        this.attributeId = hospitalConfig.getAttributeId();
        this.attributeValue = hospitalConfig.getAttributeValue();
        this.preferred = hospitalConfig.getPreferred();
        this.hospitalId = hospitalConfig.getHospitalId();
    }
}