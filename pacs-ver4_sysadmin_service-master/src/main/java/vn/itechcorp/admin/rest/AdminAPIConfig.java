package vn.itechcorp.admin.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.com.itechcorp.base.api.method.AsyncBaseDtoAPIMethod;
import vn.itechcorp.admin.jpa.entity.*;
import vn.itechcorp.admin.service.*;
import vn.itechcorp.admin.service.dto.*;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOGet;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;
import vn.itechcorp.admin.service.HospitalConfigService;

@Configuration
public class AdminAPIConfig {


    @Bean("configAttributeAPIMethod")
    public AsyncBaseDtoAPIMethod<ConfigAttributeDTOGet, ConfigAttribute, String> getConfigAttributeAPIMethod(ConfigAttributeService configAttributeService) {
        return new AsyncBaseDtoAPIMethod<>(configAttributeService);
    }

    @Bean("configHospitalAPIMethod")
    public AsyncBaseDtoAPIMethod<HospitalConfigDTOGet, HospitalConfig, Long> getConfigHospitalConfigAPIMethod(HospitalConfigService hospitalConfigService) {
        return new AsyncBaseDtoAPIMethod<>(hospitalConfigService);
    }


}
