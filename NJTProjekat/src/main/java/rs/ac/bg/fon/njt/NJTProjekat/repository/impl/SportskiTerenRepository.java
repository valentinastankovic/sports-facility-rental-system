/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.SportskiTeren;
import rs.ac.bg.fon.njt.NJTProjekat.repository.MyAppRepository;

/**
 *
 * @author AsusTuf
 */
@Repository
@Transactional
public class SportskiTerenRepository implements MyAppRepository<SportskiTeren, Integer>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SportskiTeren> findAll() {
        return entityManager.createQuery("SELECT s FROM SportskiTeren s", SportskiTeren.class ).getResultList();
    }

    @Override
    public SportskiTeren findById(Integer id) throws Exception {
        SportskiTeren sportskiTeren = entityManager.find(SportskiTeren.class, id);
        if(sportskiTeren == null){
            throw new Exception("Sportski teren nije pronadjen!");
        }
        return sportskiTeren;
    }

    @Override
    @Transactional
    public void save(SportskiTeren entity) {
        if(entity.getIdSportskiTeren()== null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
         SportskiTeren sportskiTeren = entityManager.find(SportskiTeren.class, id);
        if(sportskiTeren != null){
            entityManager.remove(sportskiTeren);
        }
    }
    
}
