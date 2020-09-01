package restapi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.models.Course;
import restapi.models.resources.CourseReq;
import restapi.models.resources.CourseResp;
import restapi.models.resources.transformer.CourseRespTrans;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.CourseRepository;
import restapi.service.businessRules.CourseBr;

/**
 * @autor: Felipe Sulzbach
 */
@Service
public class CourseService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseBr br;

    public List<CourseResp> getAll() {
        List<Course> entityList = repository.findAll();
        List<CourseResp> responseList = CourseRespTrans.create().toTransform(entityList);
        return responseList;
    }

    public CourseResp getById(long id) throws ServiceException {
        Course entity = getCourse(id);
        CourseResp response = CourseRespTrans.create().toTransform(entity);
        return response;
    }

    public Course getCourse(long id) throws ServiceException {
        Optional<Course> entityOpt = repository.findById(id);
        br.validateEntityExists(entityOpt, id);
        Course entity = entityOpt.get();
        return entity;
    }

    public CourseResp create(CourseReq req) throws ServiceException {
        br.validateNameExists(req.getName());
        Course entityCreated = repository.save(Course.valueOf(Course.create(), req));
        CourseResp response = CourseRespTrans.create().toTransform(entityCreated);
        return response;
    }

    public CourseResp update(Long id, CourseReq req) throws ServiceException {
        Course entity = getCourse(id);
        br.validateNameExists(req.getName());
        Course entityUpdated = repository.save(Course.valueOf(entity, req));
        CourseResp response = CourseRespTrans.create().toTransform(entityUpdated);
        return response;
    }

    public void remove(long id) throws ServiceException {
        Course entity = getCourse(id);
        log.info("Course removed: " + entity.toString());
        repository.delete(entity);
    }
}
