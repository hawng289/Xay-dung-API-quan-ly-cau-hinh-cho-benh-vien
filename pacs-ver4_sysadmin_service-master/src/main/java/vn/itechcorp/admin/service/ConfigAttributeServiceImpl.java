package vn.itechcorp.admin.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.com.itechcorp.base.exception.APIException;
import vn.com.itechcorp.base.service.dto.DtoUpdate;
import vn.com.itechcorp.base.service.impl.BaseDtoJpaServiceImpl;
import vn.itechcorp.admin.jpa.ConfigAttributeRepository;
import vn.itechcorp.admin.jpa.entity.ConfigAttribute;
import vn.itechcorp.admin.service.dto.ConfigAttributeDTOGet;

@Service("configAttributeService")
public class ConfigAttributeServiceImpl extends BaseDtoJpaServiceImpl<ConfigAttributeDTOGet, ConfigAttribute, String> implements ConfigAttributeService {

    private final ConfigAttributeRepository configAttributeRepository;

    public ConfigAttributeServiceImpl(ConfigAttributeRepository configAttributeRepository) {
        this.configAttributeRepository = configAttributeRepository;
    }

    @Override
    public ConfigAttributeRepository getRepository() {
        return configAttributeRepository;
    }

    @Override
    public ConfigAttributeDTOGet convert(ConfigAttribute object) {
        return new ConfigAttributeDTOGet(object);
    }

    @Override
    @CachePut(value = "configAttribute", key = "#entity.id")
    public ConfigAttributeDTOGet updateAndGet(DtoUpdate<ConfigAttribute, String> entity) throws APIException {
        return super.updateAndGet(entity);
    }

    @Override
    @CacheEvict(value = "configAttribute", key = "#entity.id")
    public String update(DtoUpdate<ConfigAttribute, String> entity) throws APIException {
        return super.update(entity);
    }

    @Override
    @CacheEvict(value = "configAttribute", key = "#id")
    public void deleteByID(String id) throws APIException {
        super.deleteByID(id);
    }

    @Override
    @CacheEvict(value = "configAttribute", key = "#id")
    public ConfigAttributeDTOGet deleteAndGet(String id) throws APIException {
        return super.deleteAndGet(id);
    }

    @Override
    @Cacheable(value = "configAttribute", key = "#id", unless = "#result == null")
    public ConfigAttributeDTOGet getById(String id) throws APIException {
        return super.getById(id);
    }

}
