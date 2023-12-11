package vn.itechcorp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.itechcorp.base.exception.APIException;
import vn.com.itechcorp.base.service.dto.BaseDtoCreate;
import vn.com.itechcorp.base.service.dto.DtoUpdate;
import vn.com.itechcorp.base.service.impl.BaseDtoJpaServiceImpl;

import vn.itechcorp.admin.service.dto.HospitalConfigDTOCreate;
import vn.itechcorp.admin.service.dto.HospitalConfigDTOGet;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;
import vn.itechcorp.admin.jpa.HospitalConfigRepo;

import javax.annotation.PostConstruct;
import java.util.List;
@Service("HospitalConfigSerImp")
public class HospitalConfigSerImp extends BaseDtoJpaServiceImpl<HospitalConfigDTOGet, HospitalConfig, Long> implements HospitalConfigService{

    private final HospitalConfigRepo hospitalConfigRepo;
    @Autowired
    public HospitalConfigSerImp(HospitalConfigRepo hospitalConfigRepo) {
        this.hospitalConfigRepo = hospitalConfigRepo;
    }

    @Override
    public HospitalConfigRepo getRepository() {
        return hospitalConfigRepo;
    }

    @Override
        public int findAllByAttributeId(String attributeId) {
        List<HospitalConfig> list = hospitalConfigRepo.findAll();
        int count = 0;
        for (HospitalConfig item: list) {
            if (item.getAttributeId()   != null && item.getAttributeId().equals(attributeId)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public HospitalConfigDTOGet convert(HospitalConfig object) {
        return new HospitalConfigDTOGet(object);
    }

    @Override
    public HospitalConfigDTOGet updateAndGet(DtoUpdate<HospitalConfig, Long> entity) throws APIException {
        return super.updateAndGet(entity);
    }
    @PostConstruct
    private void post(){
        HospitalConfigDTOCreate.setHospitalConfigSer(this);
    }

    @Override
    public HospitalConfig createEntry(BaseDtoCreate<HospitalConfig, Long> entity) throws APIException {
        return super.createEntry(entity);
    }

    @Override
    public Long update(DtoUpdate<HospitalConfig, Long> entity) throws APIException {
        return super.update(entity);
    }

    @Override
    public void deleteByID(Long id) throws APIException {
        super.deleteByID(id);
    }

    @Override
    public HospitalConfigDTOGet deleteAndGet(Long id) throws APIException {
        return super.deleteAndGet(id);
    }

    @Override

    public HospitalConfigDTOGet getById(Long id) throws APIException {
        return super.getById(id);
    }

}
