package vn.itechcorp.ris.dto;



import com.example.demo.ris.module.HospitalConfig;
import lombok.*;
import vn.com.itechcorp.base.service.dto.DtoCreate;
import vn.itechcorp.admin.service.ConfigAttributeService;
import vn.itechcorp.admin.service.dto.ConfigAttributeDTOGet;


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

    @Override
    public HospitalConfig toEntry() {
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


        }

        return new HospitalConfig(attributeId, attributeValue, preferred, hospitalId);
    }
}
