package vn.itechcorp.ris.service;



import org.springframework.stereotype.Service;
import vn.com.itechcorp.base.persistence.repository.BaseRepository;
import vn.com.itechcorp.base.service.impl.BaseDtoJpaServiceImpl;
import vn.itechcorp.ris.dto.HospitalConfigDTOGet;
import vn.itechcorp.ris.module.HospitalConfig;
import vn.itechcorp.ris.repo.HospitalConfigRepo;

import java.util.List;
@Service
public class HospitalConfigSer extends BaseDtoJpaServiceImpl<HospitalConfigDTOGet, HospitalConfig, Long> {
   HospitalConfigRepo hospitalConfigRepo;
    @Override
    public BaseRepository<HospitalConfig, Long> getRepository() {
        return this.hospitalConfigRepo;
    }

    @Override
    public HospitalConfigDTOGet convert(HospitalConfig hospitalConfig) {
        HospitalConfigDTOGet DTOGet = new HospitalConfigDTOGet();
        DTOGet.parse(hospitalConfig);
        return DTOGet;
    }

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

}
