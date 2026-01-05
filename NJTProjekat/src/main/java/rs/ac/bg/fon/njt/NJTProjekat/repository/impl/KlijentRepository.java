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
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Klijent;
import rs.ac.bg.fon.njt.NJTProjekat.repository.MyAppRepository;

/**
 *
 * @author hallo
 */
@Repository
@Transactional
public class KlijentRepository implements MyAppRepository<Klijent, Integer>{
     @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Klijent> findAll() {
        return entityManager.createQuery("SELECT k FROM Klijent k", Klijent.class ).getResultList();
    }

    @Override
    public Klijent findById(Integer id) throws Exception {
        Klijent klijent = entityManager.find(Klijent.class, id);
        if(klijent == null){
            throw new Exception("Klijent nije pronadjen!");
        }
        return klijent;
    }

    @Override
    @Transactional
    public void save(Klijent entity) {
        if(entity.getIdKlijent() == null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }
    
    @Override
    @Transactional
    public void deleteById(Integer id) {
         Klijent klijent = entityManager.find(Klijent.class, id);
        if(klijent != null){
            entityManager.remove(klijent);
        }
    }
}
