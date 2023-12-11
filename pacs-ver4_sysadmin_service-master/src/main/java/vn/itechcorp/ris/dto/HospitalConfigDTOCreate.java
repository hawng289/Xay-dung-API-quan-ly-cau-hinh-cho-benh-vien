package vn.itechcorp.ris.dto;

import lombok.*;
import vn.com.itechcorp.base.service.dto.DtoCreate;
import vn.itechcorp.admin.service.ConfigAttributeService;
import vn.itechcorp.admin.service.dto.ConfigAttributeDTOGet;
import vn.itechcorp.ris.module.HospitalConfig;
import vn.itechcorp.ris.service.HospitalConfigSer;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class HospitalConfigDTOCreate extends DtoCreate<HospitalConfig, Long> {


    private String attributeId;

    @NonNull
    private String attributeValue;

    private  Boolean preferred;

    @NonNull
    private  String hospitalId;
    private ConfigAttributeService configAttributeService;
    private HospitalConfigSer hospitalConfigSer;

    @Override
    public HospitalConfig toEntry() {
        if (attributeValue == null) {
            return null;
        }
        if (hospitalId == null) {
            return null;
        }

        if (preferred == null) {
            preferred = true;
        }

        if (attributeValue != null) {
            return null;
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
                return null;
            }

        }

        return new HospitalConfig(attributeId, attributeValue, preferred, hospitalId);
    }
}
