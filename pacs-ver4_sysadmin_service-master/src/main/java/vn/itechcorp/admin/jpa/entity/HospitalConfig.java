package vn.itechcorp.admin.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.persistence.model.BaseDbEntry;
import vn.com.itechcorp.base.persistence.model.BaseSerialIDEntry;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "gb_hospital_config")
@Getter
@Setter
@AllArgsConstructor

public class HospitalConfig extends BaseDbEntry<Long> {

    private static final long serialVersionUID = 1L;

    @Column(name = "attribute_id", nullable = false)
    private String attributeId;

    @Column(name = "attribute_value", nullable = false)
    private String attributeValue;

    @Column(name = "preferred")
    private  Boolean preferred;

    @Column(name = "hospital_id")
    private  String hospitalId;

    public HospitalConfig(Long id){
        super(id);
    }
    public HospitalConfig() {
        
    }

    public HospitalConfig(Long aLong, String attributeId, String attributeValue, Boolean preferred, String hospitalId) {
        super(aLong);
        this.attributeId = attributeId;
        this.attributeValue = attributeValue;
        this.preferred = preferred;
        this.hospitalId = hospitalId;
    }
}
