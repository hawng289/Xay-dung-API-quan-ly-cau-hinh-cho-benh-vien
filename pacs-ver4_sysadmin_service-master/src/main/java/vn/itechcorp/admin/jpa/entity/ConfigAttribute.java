package vn.itechcorp.admin.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import vn.com.itechcorp.base.persistence.model.BaseDbEntry;
import vn.itechcorp.admin.service.dto.AttributeDataType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gb_config_attribute")
@Getter
@Setter
public class ConfigAttribute extends BaseDbEntry<String> {

    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "datatype", nullable = false)
    private String datatype = AttributeDataType.STRING.name();

    @Column(name = "datatype_config")
    private String datatypeConfig;

    @Column(name = "min_occurs")
    private Integer minOccurs;

    @Column(name = "max_occurs")
    private Integer maxOccurs;

    public ConfigAttribute() {
        super();
    }

    public ConfigAttribute(String id) {
        super(id);
    }
}
