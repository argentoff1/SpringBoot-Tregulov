package com.zaurtregulov.spring.springboot.spring_course_springboot.dao;

import com.zaurtregulov.spring.springboot.spring_course_springboot.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        /*Session session = entityManager.unwrap(Session.class);

        Query<Employee> query = session.createQuery("from Employee",
                Employee.class);

        List<Employee> allEmployees = query.getResultList();*/

        Query query = entityManager.createQuery("from Employee");
        List<Employee> allEmployees = query.getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        /*Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);*/

        // При дефолтном создании записи, ID будет равен 0
        Employee newEmployee = entityManager.merge(employee);

        // ID будет инкрементироваться с помощью сеттера
        employee.setId(newEmployee.getId());
    }

    @Override
    public Employee getEmployee(int id) {
        /*Session session = entityManager.unwrap(Session.class);

        Employee employee = session.get(Employee.class, id);*/

        Employee employee = entityManager.find(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        /*Session session = entityManager.unwrap(Session.class);

        Query<Employee> query = session.createQuery("delete from Employee " +
                "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();*/

        Query query = entityManager.createQuery("delete from Employee " +
                "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
