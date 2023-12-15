package vn.itechcorp.admin.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.persistence.repository.BaseRepository;
import vn.itechcorp.admin.jpa.entity.HospitalConfig;

import java.util.List;

@Repository("hospitalConfigRepo")
public interface HospitalConfigRepository extends BaseRepository<HospitalConfig, Long> {
    @Override
    List<HospitalConfig> findAll();

    @Query("select count(u.id) from HospitalConfig u where u.hospitalId = ?1 and u.attributeId =?2")
    int countByHospitalIdAndAttributeId(String hospitalId, String attributeId);
}
