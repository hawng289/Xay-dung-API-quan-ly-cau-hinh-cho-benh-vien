package vn.itechcorp.ris.repo;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.persistence.repository.BaseRepository;
import vn.itechcorp.ris.module.HospitalConfig;

import java.util.List;

@Repository("hospital_config_repo")
public interface HospitalConfigRepo extends BaseRepository<HospitalConfig, Long> {
    @Override
    List<HospitalConfig> findAll();
}
