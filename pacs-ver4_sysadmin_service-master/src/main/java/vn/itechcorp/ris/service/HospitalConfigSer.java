package com.example.demo.ris.service;

import com.example.demo.ris.dto.HospitalConfigDTOGet;
import com.example.demo.ris.module.HospitalConfig;
import vn.com.itechcorp.base.persistence.repository.BaseRepository;
import vn.com.itechcorp.base.service.BaseDtoService;
import vn.com.itechcorp.base.service.impl.BaseDtoJpaServiceImpl;
import com.example.demo.ris.repo.HospitalConfigRepo;

public class HospitalConfigSer extends BaseDtoJpaServiceImpl<com.example.demo.ris.dto.HospitalConfigDTOGet, com.example.demo.ris.module.HospitalConfig, Long> {
   HospitalConfigRepo hospitalConfigRepo;
    @Override
    public BaseRepository<HospitalConfig, Long> getRepository() {
        return this.hospitalConfigRepo;
    }

    @Override
    public HospitalConfigDTOGet convert(HospitalConfig hospitalConfig) {
        HospitalConfigDTOGet DTOGet = new HospitalConfigDTOGet();
        DTOGet.parse(hospitalConfig);
        return DTOGet;
    }

    public int findAllByAttributeId(String attributeId) {

        return hospitalConfigRepo.findAll
    }
}
