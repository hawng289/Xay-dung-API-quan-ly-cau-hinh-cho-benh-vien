package vn.itechcorp.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import vn.itechcorp.admin.jpa.HospitalConfigRepository;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;
import vn.itechcorp.admin.service.HospitalConfigService;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOCreate;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOGet;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOUpdate;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
@SpringBootTest
public class AdminApplicationTest {
    @Autowired
    private HospitalConfigService hospitalConfigService;
    @Autowired
    private HospitalConfigRepository hospitalConfigRepository;

    @Test
    @Sql("sql/schema.sql")

    public void test() throws Exception {

        // Create_Test
        HospitalConfigDTOCreate hospitalConfigDTOCreate1 = new HospitalConfigDTOCreate(1L, "003", "12", true, "001");
        HospitalConfig  hospitalConfigCreate1 = hospitalConfigDTOCreate1.toEntry();
        hospitalConfigService.create(hospitalConfigDTOCreate1);
        Assertions.assertEquals(hospitalConfigCreate1, hospitalConfigRepository.findById(1L).get());

        HospitalConfigDTOCreate hospitalConfigDTOCreate2 = new HospitalConfigDTOCreate(2L, "003", "12", true, "001");
        HospitalConfig  hospitalConfigCreate2 = hospitalConfigDTOCreate2.toEntry();
        hospitalConfigService.create(hospitalConfigDTOCreate2);
        Assertions.assertEquals(hospitalConfigCreate2, hospitalConfigRepository.findById(2L).get());

        HospitalConfigDTOCreate hospitalConfigDTOCreate3 = new HospitalConfigDTOCreate(3L, "001", "true", true, "001");
        HospitalConfig  hospitalConfigCreate3 = hospitalConfigDTOCreate3.toEntry();
        hospitalConfigService.create(hospitalConfigDTOCreate3);
        Assertions.assertEquals(hospitalConfigCreate3, hospitalConfigRepository.findById(3L).get());

        // Update_Test
        HospitalConfigDTOUpdate hospitalConfigDTOUpdate1 = new HospitalConfigDTOUpdate(1L);
        hospitalConfigDTOUpdate1.setAttributeValue("12");
        hospitalConfigService.updateAndGet(hospitalConfigDTOUpdate1);
        Assertions.assertEquals("12", hospitalConfigRepository.findById(1L).get().getAttributeValue());


        // Create_Validate
        HospitalConfigDTOCreate hospitalConfigDTOCreate4 = new HospitalConfigDTOCreate(4L, "003", "17", true, "001");
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->  hospitalConfigService.create(hospitalConfigDTOCreate4));
        assertEquals("Vuot qua so luong ConfigAttribute", exception.getMessage());

        //Update_Validate
        HospitalConfigDTOUpdate hospitalConfigDTOUpdate2 = new HospitalConfigDTOUpdate(3L);
        hospitalConfigDTOUpdate2.setAttributeId("003");
        hospitalConfigDTOUpdate2.setAttributeValue("16");
        Throwable exception2 = assertThrows(IllegalArgumentException.class, () ->  hospitalConfigService.updateAndGet(hospitalConfigDTOUpdate2));
        assertEquals("Vuot qua so luong ConfigAttribute", exception2.getMessage());

        HospitalConfigDTOUpdate hospitalConfigDTOUpdate3 = new HospitalConfigDTOUpdate(3L);
        hospitalConfigDTOUpdate3.setAttributeId("001");
        hospitalConfigDTOUpdate3.setAttributeValue("16");
        Throwable exception3 = assertThrows(IllegalArgumentException.class, () ->  hospitalConfigService.updateAndGet(hospitalConfigDTOUpdate3));
        assertEquals("ConfigAttribute co datatype la BOOLEAN nen attributeValue phai la true/false", exception3.getMessage());
        // Test_countByHospitalIdAndAttributeId
        Assertions.assertEquals(2,hospitalConfigService.countByHospitalIdAndAttributeId("001", "003"));
        //Delete_Test
        hospitalConfigService.deleteAndGet(2L);
        HospitalConfigDTOGet hospitalConfigDTOGet = hospitalConfigService.getById(2L);
        Assertions.assertEquals(null,hospitalConfigDTOGet);

    }
}