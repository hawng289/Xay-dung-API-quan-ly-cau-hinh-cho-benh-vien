package com.example.demo.ris.repo;

import com.example.demo.ris.module.HospitalConfig;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.persistence.repository.BaseRepository;

import java.util.List;

@Repository("hospital_config_repo")
public interface HospitalConfigRepo extends BaseRepository<HospitalConfig, Long> {
    @Override
    List<HospitalConfig> findAll(Specification<HospitalConfig> specification);
}
