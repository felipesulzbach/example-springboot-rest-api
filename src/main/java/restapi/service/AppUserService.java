package restapi.service;

import java.time.LocalDateTime;
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
import restapi.repository.AppUserRepository;
import restapi.util.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
@Service
public class AppUserService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private AppUserRepository repository;

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
        AppUser entityCreated = repository.save(
                AppUser.create().withName(req.getName()).withKey(req.getKey()).withPerson(getPerson(req.getPersonId()))
                        .withProfile(getProfile(req.getProfileId())).withRegistrationDate(LocalDateTime.now())
                        .withStartDate(LocalDateTime.now()).withEndDate(req.getExpirationDate()));
        AppUserResp response = AppUserRespTrans.create().toTransform(entityCreated);
        return response;
    }

    public AppUserResp update(Long id, AppUserReq req) throws ServiceException {
        AppUser entity = getAppUser(id);
        AppUser entityUpdated = repository.save(entity.withName(req.getName()).withKey(req.getKey())
                .withPerson(getPerson(req.getPersonId())).withProfile(getProfile(req.getProfileId()))
                .withStartDate(LocalDateTime.now()).withEndDate(req.getExpirationDate()));
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
        if (entityOpt == null || !entityOpt.isPresent())
            throw ServiceException.get("ENTITY_NOT_FOUND", String.valueOf(id), "User");
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
