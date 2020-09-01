package restapi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.models.AppUser;
import restapi.models.Person;
import restapi.models.Profile;
import restapi.models.resources.AppUserReq;
import restapi.models.resources.AppUserResp;
import restapi.models.resources.transformer.AppUserRespTrans;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.AppUserRepository;
import restapi.service.businessRules.AppUserBr;

/**
 * @autor: Felipe Sulzbach
 */
@Service
public class AppUserService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private AppUserBr br;

    @Autowired
    private PersonService personService;

    @Autowired
    private ProfileService profileService;

    public List<AppUserResp> getAll() {
        List<AppUser> entityList = repository.findAll();
        List<AppUserResp> responseList = AppUserRespTrans.create().toTransform(entityList);
        return responseList;
    }

    public AppUserResp getById(long id) throws ServiceException {
        AppUser entity = getAppUser(id);
        AppUserResp response = AppUserRespTrans.create().toTransform(entity);
        return response;
    }

    public AppUserResp create(AppUserReq req) throws ServiceException {
        br.validateNameExists(req.getName());
        AppUser entityCreated = repository.save(
                AppUser.valueOf(AppUser.create(), req, getPerson(req.getPersonId()), getProfile(req.getProfileId())));
        AppUserResp response = AppUserRespTrans.create().toTransform(entityCreated);
        return response;
    }

    public AppUserResp update(Long id, AppUserReq req) throws ServiceException {
        AppUser entity = getAppUser(id);
        br.validateNameExists(req.getName());
        AppUser entityUpdated = repository
                .save(AppUser.valueOf(entity, req, getPerson(req.getPersonId()), getProfile(req.getProfileId())));
        AppUserResp response = AppUserRespTrans.create().toTransform(entityUpdated);
        return response;
    }

    public void remove(long id) throws ServiceException {
        AppUser entity = getAppUser(id);
        log.info("User removed: " + entity.toString());
        repository.delete(entity);
    }

    private AppUser getAppUser(long id) throws ServiceException {
        Optional<AppUser> entityOpt = repository.findById(id);
        br.validateEntityExists(entityOpt, id);
        AppUser entity = entityOpt.get();
        return entity;
    }

    private Person getPerson(final Long id) throws ServiceException {
        Person entity = null;
        if (id != null) {
            entity = personService.getPerson(id);
        }
        return entity;
    }

    private Profile getProfile(final Long id) throws ServiceException {
        Profile entity = null;
        if (id != null) {
            entity = profileService.getProfile(id);
        }
        return entity;
    }
}
