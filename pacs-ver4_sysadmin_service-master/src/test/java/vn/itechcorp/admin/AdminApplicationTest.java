package vn.itechcorp.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import vn.com.itechcorp.base.api.method.AsyncBaseDtoAPIMethod;
import vn.itechcorp.admin.jpa.HospitalConfigRepo;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;
import vn.itechcorp.admin.rest.AdminController;
import vn.itechcorp.admin.rest.Controller;
import vn.itechcorp.admin.service.HospitalConfigService;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOCreate;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOGet;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOUpdate;

import java.util.ResourceBundle;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminApplicationTest {
    @Autowired
    private HospitalConfigService hospitalConfigService;
    @Autowired
    private HospitalConfigRepo hospitalConfigRepo;

    @Test
    @Sql("sql/schema.sql")

    public void test() throws Exception {

        // Create_Test
        HospitalConfigDTOCreate hospitalConfigDTOCreate1 = new HospitalConfigDTOCreate(1L, "003", "12", true, "001");
        HospitalConfig  hospitalConfigCreate1 = hospitalConfigDTOCreate1.toEntry();
        hospitalConfigService.create(hospitalConfigDTOCreate1);
        Assertions.assertEquals(hospitalConfigCreate1, hospitalConfigRepo.findById(1L).get());

        HospitalConfigDTOCreate hospitalConfigDTOCreate2 = new HospitalConfigDTOCreate(2L, "003", "12", true, "001");
        HospitalConfig  hospitalConfigCreate2 = hospitalConfigDTOCreate2.toEntry();
        hospitalConfigService.create(hospitalConfigDTOCreate2);
        Assertions.assertEquals(hospitalConfigCreate2, hospitalConfigRepo.findById(2L).get());

        HospitalConfigDTOCreate hospitalConfigDTOCreate3 = new HospitalConfigDTOCreate(3L, "001", "true", true, "001");
        HospitalConfig  hospitalConfigCreate3 = hospitalConfigDTOCreate3.toEntry();
        hospitalConfigService.create(hospitalConfigDTOCreate3);
        Assertions.assertEquals(hospitalConfigCreate3, hospitalConfigRepo.findById(3L).get());

        // Update_Test
        HospitalConfigDTOUpdate hospitalConfigDTOUpdate1 = new HospitalConfigDTOUpdate(1L);
        hospitalConfigDTOUpdate1.setAttributeValue("12");
        hospitalConfigService.updateAndGet(hospitalConfigDTOUpdate1);
        Assertions.assertEquals("12", hospitalConfigRepo.findById(1L).get().getAttributeValue());


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


        //Delete_Test
        hospitalConfigService.deleteAndGet(2L);
        HospitalConfigDTOGet hospitalConfigDTOGet = hospitalConfigService.getById(2L);
        Assertions.assertEquals(null,hospitalConfigDTOGet);


    }
}