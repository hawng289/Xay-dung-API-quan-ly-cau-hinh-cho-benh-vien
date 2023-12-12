package vn.itechcorp.admin.service;

import vn.com.itechcorp.base.service.BaseDtoService;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOGet;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;


public interface HospitalConfigService extends BaseDtoService<HospitalConfigDTOGet, HospitalConfig, Long> {
    int findAllByAttributeId(String attributeId);



}

