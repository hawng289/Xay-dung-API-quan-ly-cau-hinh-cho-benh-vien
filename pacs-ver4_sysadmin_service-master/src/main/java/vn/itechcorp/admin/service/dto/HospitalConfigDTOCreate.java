package vn.itechcorp.admin.service.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.itechcorp.base.service.dto.DtoCreate;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;
import vn.itechcorp.admin.service.ConfigAttributeServiceImpl;
import vn.itechcorp.admin.service.HospitalConfigSerImp;

@Service("toCreate")
@Getter
@Setter
public class HospitalConfigDTOCreate extends DtoCreate<HospitalConfig, Long> {

    private String attributeId;

    @NonNull
    private String attributeValue;

    private  Boolean preferred;

    @NonNull
    private  String hospitalId;

    private static ConfigAttributeServiceImpl configAttributeService = null;

    private static HospitalConfigSerImp hospitalConfigSer = null;

    public static void setHospitalConfigSer(HospitalConfigSerImp hospitalConfigSer1 ) {
        hospitalConfigSer = hospitalConfigSer1;
    }

    public static void setConfigAttributeService(ConfigAttributeServiceImpl configAttributeService1 ) {
        configAttributeService = configAttributeService1;
    }



    @Override
    public HospitalConfig toEntry() {
        System.out.println(configAttributeService);

        if (preferred == null) {
            preferred = true;
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
                System.err.println("Qua so luong");
                return null;
            }

        }
        HospitalConfig object = new HospitalConfig();
        object.setId(getId());
        object.setAttributeId(attributeId);
        object.setPreferred(preferred);
        object.setHospitalId(hospitalId);
        object.setAttributeValue(attributeValue);
        System.out.println(object);


        return object;
    }
}
