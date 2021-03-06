package restapi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.models.Person;
import restapi.models.resources.PersonReq;
import restapi.models.resources.PersonResp;
import restapi.models.resources.transformer.PersonRespTrans;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.PersonRepository;
import restapi.service.businessRules.PersonBr;

/**
 * @autor: Felipe Sulzbach
 */
@Service
public class PersonService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonBr br;

    public List<PersonResp> getAll() {
        List<Person> entityList = repository.findAll();
        List<PersonResp> responseList = PersonRespTrans.create().toTransform(entityList);
        return responseList;
    }

    public PersonResp getById(long id) throws ServiceException {
        Person entity = getPerson(id);
        PersonResp response = PersonRespTrans.create().toTransform(entity);
        return response;
    }

    public Person getPerson(long id) throws ServiceException {
        Optional<Person> entityOpt = repository.findById(id);
        br.validateEntityExists(entityOpt, id);
        Person entity = entityOpt.get();
        return entity;
    }

    public PersonResp create(PersonReq req) throws ServiceException {
        br.validateNameExists(req.getName());
        Person entityCreated = repository.save(Person.valueOf(Person.create(), req));
        PersonResp response = PersonRespTrans.create().toTransform(entityCreated);
        return response;
    }

    public PersonResp update(Long id, PersonReq req) throws ServiceException {
        Person entity = getPerson(id);
        br.validateNameExists(req.getName());
        Person entityUpdated = repository.save(Person.valueOf(entity, req));
        PersonResp response = PersonRespTrans.create().toTransform(entityUpdated);
        return response;
    }

    public void remove(long id) throws ServiceException {
        Person entity = getPerson(id);
        log.info("Person removed: " + entity.toString());
        repository.delete(entity);
    }
}
