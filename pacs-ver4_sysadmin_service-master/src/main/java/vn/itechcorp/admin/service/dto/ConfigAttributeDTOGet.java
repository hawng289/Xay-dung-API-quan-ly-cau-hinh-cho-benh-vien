package vn.itechcorp.admin.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoGet;
import vn.itechcorp.admin.jpa.entity.ConfigAttribute;

@Getter
@Setter
@NoArgsConstructor
public class ConfigAttributeDTOGet extends DtoGet<ConfigAttribute, String> {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    private String datatype;

    private String datatypeConfig;

    private Integer minOccurs;

    private Integer maxOccurs;

    public ConfigAttributeDTOGet(ConfigAttribute object) {
        super(object);
    }

    @Override
    public void parse(ConfigAttribute object) {
        this.name = object.getName();
        this.description = object.getDescription();
        this.datatype = object.getDatatype();
        this.datatypeConfig = object.getDatatypeConfig();
        this.minOccurs = object.getMinOccurs();
        this.maxOccurs = object.getMaxOccurs();
    }
}
