package restapi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.models.Course;
import restapi.models.Teacher;
import restapi.models.resources.TeacherReq;
import restapi.models.resources.TeacherResp;
import restapi.models.resources.transformer.TeacherRespTrans;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.TeacherRepository;
import restapi.service.businessRules.TeacherBr;

/**
 * @autor: Felipe Sulzbach
 */
@Service
public class TeacherService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TeacherBr br;

    @Autowired
    private CourseService CourseService;

    public List<TeacherResp> getAll() {
        List<Teacher> entityList = repository.findAll();
        List<TeacherResp> responseList = TeacherRespTrans.create().toTransform(entityList);
        return responseList;
    }

    public TeacherResp getById(long id) throws ServiceException {
        Teacher entity = getTeacher(id);
        TeacherResp response = TeacherRespTrans.create().toTransform(entity);
        return response;
    }

    public Teacher getTeacher(long id) throws ServiceException {
        Optional<Teacher> entityOpt = repository.findById(id);
        br.validateEntityExists(entityOpt, id);
        Teacher entity = entityOpt.get();
        return entity;
    }

    public TeacherResp create(TeacherReq req) throws ServiceException {
        br.validateNameExists(req.getName());
        Course course = getCourse(req.getCourseId());
        Teacher entityCreated = repository.save(Teacher.valueOf(Teacher.create(), req, course));
        TeacherResp response = TeacherRespTrans.create().toTransform(entityCreated);
        return response;
    }

    public TeacherResp update(Long id, TeacherReq req) throws ServiceException {
        Teacher entity = getTeacher(id);
        br.validateNameExists(req.getName());
        Course course = getCourse(req.getCourseId());
        Teacher entityUpdated = repository.save(Teacher.valueOf(entity, req, course));
        TeacherResp response = TeacherRespTrans.create().toTransform(entityUpdated);
        return response;
    }

    public void remove(long id) throws ServiceException {
        Teacher entity = getTeacher(id);
        log.info("Teacher removed: " + entity.toString());
        repository.delete(entity);
    }

    private Course getCourse(final Long id) throws ServiceException {
        Course entity = null;
        if (id != null) {
            entity = CourseService.getCourse(id);
        }
        return entity;
    }
}
