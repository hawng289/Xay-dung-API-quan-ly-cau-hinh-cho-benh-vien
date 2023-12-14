package vn.itechcorp.admin.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.persistence.repository.BaseRepository;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;

import java.util.List;

@Repository("hospitalConfigRepo")
public interface HospitalConfigRepo extends BaseRepository<HospitalConfig, Long> {
    @Override
    List<HospitalConfig> findAll();

    @Override
    List<HospitalConfig> findAll(Specification<HospitalConfig> specification);
}
