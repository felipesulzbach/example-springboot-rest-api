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
import restapi.service.businessRules.ProfileBr;
import restapi.util.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
@Service
public class ProfileService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private ProfileRepository repository;

    @Autowired
    private ProfileBr br;

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
        br.validateEntityExists(entityOpt, id);
        Profile entity = entityOpt.get();
        return entity;
    }

    public ProfileResp create(ProfileReq req) throws ServiceException {
        br.validateNameExists(req.getName());
        Profile entityCreated = repository.save(Profile.valueOf(Profile.create(), req));
        ProfileResp response = ProfileRespTrans.create().toTransform(entityCreated);
        return response;
    }

    public ProfileResp update(Long id, ProfileReq req) throws ServiceException {
        Profile entity = getProfile(id);
        br.validateNameExists(req.getName());
        Profile entityUpdated = repository.save(Profile.valueOf(entity, req));
        ProfileResp response = ProfileRespTrans.create().toTransform(entityUpdated);
        return response;
    }

    public void remove(long id) throws ServiceException {
        Profile entity = getProfile(id);
        log.info("Profile removed: " + entity.toString());
        repository.delete(entity);
    }
}
