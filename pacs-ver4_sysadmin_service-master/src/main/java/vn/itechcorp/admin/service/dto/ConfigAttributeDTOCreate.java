package vn.itechcorp.admin.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoCreate;
import vn.itechcorp.admin.jpa.entity.ConfigAttribute;


import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ConfigAttributeDTOCreate extends DtoCreate<ConfigAttribute, String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private AttributeDataType datatype;

    private String datatypeConfig;

    @NotNull
    private Integer minOccurs;

    private Integer maxOccurs;

    @Override
    public ConfigAttribute toEntry() {
        ConfigAttribute object = new ConfigAttribute();
        object.setId(getId());
        object.setName(name);
        object.setDescription(description);
        object.setDatatype(datatype.name());
        object.setDatatypeConfig(datatypeConfig);
        object.setMinOccurs(minOccurs);
        object.setMaxOccurs(maxOccurs);
        return object;
    }
}
