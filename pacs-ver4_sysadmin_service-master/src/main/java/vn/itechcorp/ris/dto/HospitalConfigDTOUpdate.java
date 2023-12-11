package vn.itechcorp.ris.dto;

import lombok.NonNull;
import vn.com.itechcorp.base.service.dto.DtoUpdate;

import vn.itechcorp.admin.service.ConfigAttributeService;

import vn.itechcorp.admin.service.dto.ConfigAttributeDTOGet;

import vn.itechcorp.ris.module.HospitalConfig;
import vn.itechcorp.ris.service.HospitalConfigSer;

public class HospitalConfigDTOUpdate extends DtoUpdate<HospitalConfig, Long> {
    private String attributeId;

    @NonNull
    private String attributeValue;
    private ConfigAttributeService configAttributeService;

    private Boolean preferred;

    @NonNull
    private String hospitalId;

    private ConfigAttributeDTOGet configAttributeDTOGet = new ConfigAttributeDTOGet();
    private HospitalConfigSer hospitalConfigSer;

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
        if (configAttributeService != null) {
            ConfigAttributeDTOGet configAttribute = configAttributeService.getById(attributeId);
            String type = configAttribute.getDatatype();
            if (type != null) {
                if (type.equals("BOOLEAN")) {
                    if (attributeValue.trim().toLowerCase().equals("true")) {
                        attributeValue = "true";
                    } else {
                        attributeValue = "false";
                    }
                }
                if (type.equals("INT") || type.equals("INTEGER")) {
                    try {
                        attributeValue = Integer.parseInt(attributeValue) + "";
                    } catch (NumberFormatException e) {
                        System.err.println("attributeValue phai la dang int");
                    }

                }
                if (type.equals("DOUBLE") || type.equals("FLOAT") || type.equals("REAL")) {
                    try {
                        attributeValue = Double.parseDouble(attributeValue) + "";
                    } catch (NumberFormatException e) {
                        System.err.println("attributeValue phai la dang so thuc");
                    }

                }
            }
            Integer maxOccurs = configAttribute.getMaxOccurs();
            if (hospitalConfigSer.findAllByAttributeId(attributeId) >= maxOccurs) {
                return false;
            }

        }
        return true;

    }

}
