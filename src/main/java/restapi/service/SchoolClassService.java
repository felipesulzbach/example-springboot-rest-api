package restapi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.models.Course;
import restapi.models.SchoolClass;
import restapi.models.resources.SchoolClassReq;
import restapi.models.resources.SchoolClassResp;
import restapi.models.resources.transformer.SchoolClassRespTrans;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.SchoolClassRepository;
import restapi.service.businessRules.SchoolClassBr;

/**
 * @autor: Felipe Sulzbach
 */
@Service
public class SchoolClassService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private SchoolClassRepository repository;

    @Autowired
    private SchoolClassBr br;

    @Autowired
    private CourseService courseService;

    public List<SchoolClassResp> getAll() {
        List<SchoolClass> entityList = repository.findAll();
        List<SchoolClassResp> responseList = SchoolClassRespTrans.create().toTransform(entityList);
        return responseList;
    }

    public SchoolClassResp getById(long id) throws ServiceException {
        SchoolClass entity = getSchoolClass(id);
        SchoolClassResp response = SchoolClassRespTrans.create().toTransform(entity);
        return response;
    }

    public SchoolClass getSchoolClass(long id) throws ServiceException {
        Optional<SchoolClass> entityOpt = repository.findById(id);
        br.validateEntityExists(entityOpt, id);
        SchoolClass entity = entityOpt.get();
        return entity;
    }

    public SchoolClassResp create(SchoolClassReq req) throws ServiceException {
        Course course = getCourse(req.getCourseId());
        SchoolClass entityCreated = repository.save(SchoolClass.valueOf(SchoolClass.create(), req, course));
        SchoolClassResp response = SchoolClassRespTrans.create().toTransform(entityCreated);
        return response;
    }

    public SchoolClassResp update(Long id, SchoolClassReq req) throws ServiceException {
        SchoolClass entity = getSchoolClass(id);
        Course course = getCourse(req.getCourseId());
        SchoolClass entityUpdated = repository.save(SchoolClass.valueOf(entity, req, course));
        SchoolClassResp response = SchoolClassRespTrans.create().toTransform(entityUpdated);
        return response;
    }

    public void remove(long id) throws ServiceException {
        SchoolClass entity = getSchoolClass(id);
        log.info("Class removed: " + entity.toString());
        repository.delete(entity);
    }

    private Course getCourse(final Long id) throws ServiceException {
        Course entity = null;
        if (id != null) {
            entity = courseService.getCourse(id);
        }
        return entity;
    }
}
