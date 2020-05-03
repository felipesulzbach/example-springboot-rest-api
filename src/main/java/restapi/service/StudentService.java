package restapi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.models.SchoolClass;
import restapi.models.Student;
import restapi.models.resources.StudentReq;
import restapi.models.resources.StudentResp;
import restapi.models.resources.transformer.StudentRespTrans;
import restapi.repository.StudentRepository;
import restapi.util.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
@Service
public class StudentService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private StudentRepository repository;

    @Autowired
    private SchoolClassService schoolClassService;

    public List<StudentResp> getAll() {
        List<Student> entityList = repository.findAll();
        List<StudentResp> responseList = StudentRespTrans.create().toTransform(entityList);
        return responseList;
    }

    public StudentResp getById(long id) throws ServiceException {
        Student entity = getStudent(id);
        StudentResp response = StudentRespTrans.create().toTransform(entity);
        return response;
    }

    public Student getStudent(long id) throws ServiceException {
        Optional<Student> entityOpt = repository.findById(id);
        if (entityOpt == null || !entityOpt.isPresent())
            throw ServiceException.get("ENTITY_NOT_FOUND", String.valueOf(id), "Student");
        Student entity = entityOpt.get();

        return entity;
    }

    public StudentResp create(StudentReq req) throws ServiceException {
        SchoolClass schoolClass = getSchoolClass(req.getSchoolClassId());
        Student entityCreated = repository.save(Student.valueOf(Student.create(), req, schoolClass));
        StudentResp response = StudentRespTrans.create().toTransform(entityCreated);
        return response;
    }

    public StudentResp update(Long id, StudentReq req) throws ServiceException {
        Student entity = getStudent(id);
        SchoolClass schoolClass = getSchoolClass(req.getSchoolClassId());
        Student entityUpdated = repository.save(Student.valueOf(entity, req, schoolClass));
        StudentResp response = StudentRespTrans.create().toTransform(entityUpdated);
        return response;
    }

    public void remove(long id) throws ServiceException {
        Student entity = getStudent(id);
        log.info("Student removed: " + entity.toString());
        repository.delete(entity);
    }

    private SchoolClass getSchoolClass(final Long id) throws ServiceException {
        SchoolClass entity = null;
        if (id != null) {
            entity = schoolClassService.getSchoolClass(id);
        }

        return entity;
    }
}
