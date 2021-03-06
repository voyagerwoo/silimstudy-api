package com.silimstudy.study;

import com.silimstudy.auth.User;
import com.silimstudy.study.persistence.Study;
import com.silimstudy.study.persistence.StudyRepository;
import com.silimstudy.study.request.StudyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yeomyeongwoo on 2017. 1. 6..
 */
@Service
public class StudyServiceImpl implements StudyService {
    private StudyRepository studyRepository;

    @Autowired
    public StudyServiceImpl(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public List<Study> search() {
        return studyRepository.findAll();
    }

    @Override
    public List<Study> search(long userId) {
        return studyRepository.findByAdminId(userId);
    }

    @Override
    public void register(User user, StudyRequest request) {
        studyRepository.save(new Study(request.getTitle(), request.getContents(), user.getId()));
    }

    @Override
    public Study find(long studyId) {
        return studyRepository.findOne(studyId);
    }

    @Override
    public void remove(Study study) {
        study.setActive(false);
        studyRepository.save(study);
    }
}
