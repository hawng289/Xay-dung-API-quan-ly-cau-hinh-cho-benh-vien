package vn.itechcorp.admin.jpa;

import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.persistence.repository.BaseRepository;
import vn.itechcorp.admin.jpa.entity.ConfigAttribute;


@Repository("configAttributeRepository")
public interface ConfigAttributeRepository extends BaseRepository<ConfigAttribute, String> {
}
