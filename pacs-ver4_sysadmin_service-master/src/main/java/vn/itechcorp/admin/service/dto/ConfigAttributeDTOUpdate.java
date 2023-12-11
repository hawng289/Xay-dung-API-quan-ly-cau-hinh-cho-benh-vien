package vn.itechcorp.admin.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoUpdate;
import vn.itechcorp.admin.jpa.entity.ConfigAttribute;

@Getter
@Setter
@NoArgsConstructor
public class ConfigAttributeDTOUpdate extends DtoUpdate<ConfigAttribute, String> {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    private AttributeDataType datatype;

    private String datatypeConfig;

    private Integer minOccurs;

    private Integer maxOccurs;

    @Override
    public boolean apply(ConfigAttribute object) {
        if (name != null && !name.equals(object.getName())) {
            object.setName(name);
            return true;
        }

        if (description != null && !description.equals(object.getDescription())) {
            object.setDescription(description);
            return true;
        }

        if (datatype != null && !datatype.name().equals(object.getDatatype())) {
            object.setDatatype(datatype.name());
            return true;
        }

        if (datatypeConfig != null && !datatypeConfig.equals(object.getDatatypeConfig())) {
            object.setDatatypeConfig(datatypeConfig);
            return true;
        }

        if (minOccurs != null && minOccurs >= 0 && !minOccurs.equals(object.getMinOccurs())) {
            object.setMinOccurs(minOccurs);
            return true;
        }

        if (maxOccurs != null && !maxOccurs.equals(object.getMaxOccurs())) {
            if (maxOccurs <= 0) object.setMaxOccurs(null);
            else object.setMaxOccurs(maxOccurs);
            return true;
        }

        return false;
    }
}
