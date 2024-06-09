package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {
    @Override
    public List<DoctorEntity> findBySpecialization(Specialization specialization) {
        TypedQuery<DoctorEntity> query = entityManager.createQuery(
                "SELECT d FROM DoctorEntity d WHERE d.specialization = :specialization",
                DoctorEntity.class
        );
        query.setParameter("specialization", specialization);
        return query.getResultList();
    }

    @Override
    public long countNumOfVisitsWithPatient(String docFirstName, String docLastName, String patientFirstName, String patientLastName) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(v) FROM VisitEntity v WHERE v.doctor.firstName = :docFirstName AND v.doctor.lastName = :docLastName AND v.patient.firstName = :patientFirstName AND v.patient.lastName = :patientLastName",
                Long.class
        );
        query.setParameter("docFirstName", docFirstName);
        query.setParameter("docLastName", docLastName);
        query.setParameter("patientFirstName", patientFirstName);
        query.setParameter("patientLastName", patientLastName);
        return query.getSingleResult();
    }


}
