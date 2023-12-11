package com.example.demo.ris.dto;

import com.example.demo.ris.module.HospitalConfig;
import lombok.NonNull;
import vn.com.itechcorp.base.service.dto.DtoUpdate;
import vn.itechcorp.admin.jpa.entity.ConfigAttribute;
import vn.itechcorp.admin.service.ConfigAttributeService;
import vn.itechcorp.admin.service.dto.ConfigAttributeDTOCreate;
import vn.itechcorp.admin.service.dto.ConfigAttributeDTOGet;
import vn.itechcorp.admin.service.dto.ConfigAttributeDTOUpdate;

public class HospitalConfigDTOUpdate extends DtoUpdate<HospitalConfig, Long> {
    private String attributeId;

    @NonNull
    private ConfigAttribute attributeValue;
    private ConfigAttributeService configAttributeService;

    private  Boolean preferred;

    @NonNull
    private  String hospitalId;

    private ConfigAttributeDTOGet configAttributeDTOGet = new ConfigAttributeDTOGet();
    @Override
    public boolean apply(HospitalConfig hospitalConfig) {

        // Check null

        if (hospitalId == null) {
            return false;
        }

        if (preferred == null) {
            preferred = true;
        }

        if (attributeValue != null) {
            return false;
        }


    }
}
