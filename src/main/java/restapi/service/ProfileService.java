package restapi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.models.Profile;
import restapi.models.resources.ProfileReq;
import restapi.models.resources.ProfileResp;
import restapi.models.resources.transformer.ProfileRespTrans;
import restapi.repository.ProfileRepository;
import restapi.util.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
@Service
public class ProfileService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private ProfileRepository repository;

    public List<ProfileResp> getAll() {
        List<Profile> entityList = repository.findAll();
        List<ProfileResp> responseList = ProfileRespTrans.create().toTransform(entityList);
        return responseList;
    }

    public ProfileResp getById(long id) throws ServiceException {
        Profile entity = getProfile(id);
        ProfileResp response = ProfileRespTrans.create().toTransform(entity);
        return response;
    }

    public Profile getProfile(long id) throws ServiceException {
        Optional<Profile> entityOpt = repository.findById(id);
        if (entityOpt == null || !entityOpt.isPresent())
            throw ServiceException.get("ENTITY_NOT_FOUND", String.valueOf(id), "Profile");
        Profile entity = entityOpt.get();

        return entity;
    }

    public ProfileResp create(ProfileReq req) throws ServiceException {
        Profile entityCreated = repository.save(Profile.create().withName(req.getName()).withCode(req.getCode())
                .withAccessPermission(req.getAccessPermission()));
        ProfileResp response = ProfileRespTrans.create().toTransform(entityCreated);
        return response;
    }

    public ProfileResp update(Long id, ProfileReq req) throws ServiceException {
        Profile entity = getProfile(id);
        Profile entityUpdated = repository.save(
                entity.withName(req.getName()).withCode(req.getCode()).withAccessPermission(req.getAccessPermission()));
        ProfileResp response = ProfileRespTrans.create().toTransform(entityUpdated);
        return response;
    }

    public void remove(long id) throws ServiceException {
        Profile entity = getProfile(id);
        log.info("Profile removed: " + entity.toString());
        repository.delete(entity);
    }
}
