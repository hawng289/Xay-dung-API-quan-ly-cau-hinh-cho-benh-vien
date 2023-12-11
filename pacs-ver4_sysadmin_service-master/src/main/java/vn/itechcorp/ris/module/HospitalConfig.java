package com.example.demo.ris.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.persistence.model.BaseDbEntry;
import vn.com.itechcorp.base.persistence.model.BaseSerialIDEntry;
import vn.com.itechcorp.base.persistence.model.interfaces.BaseEntity;
import vn.itechcorp.admin.jpa.entity.ConfigAttribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "gb_hospital_config")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalConfig extends BaseSerialIDEntry {

    private static final long serialVersionUID = 1L;


    @Column(name = "attribute_id", nullable = false)
    private String attributeId;

    @Column(name = "attribute_value", nullable = false)
    private String attributeValue;

    @Column(name = "preferred")
    private  Boolean preferred;

    @Column(name = "hospital_id")
    private  String hospitalId;


}
