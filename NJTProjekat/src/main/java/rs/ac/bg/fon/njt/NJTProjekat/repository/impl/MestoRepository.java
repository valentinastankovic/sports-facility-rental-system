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
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Mesto;
import rs.ac.bg.fon.njt.NJTProjekat.repository.MyAppRepository;

/**
 *
 * @author hallo
 */
@Repository
@Transactional
public class MestoRepository implements MyAppRepository<Mesto, Integer>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Mesto> findAll() {
        return entityManager.createQuery("SELECT m FROM Mesto m", Mesto.class ).getResultList();
    }

    @Override
    public Mesto findById(Integer id) throws Exception {
        Mesto mesto = entityManager.find(Mesto.class, id);
        if(mesto == null){
            throw new Exception("Mesto nije pronadjeno!");
        }
        return mesto;
    }

    @Override
    public void save(Mesto entity) {
        if(entity.getIdMesto() == null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteById(Integer id) {
         Mesto mesto = entityManager.find(Mesto.class, id);
        if(mesto != null){
            entityManager.remove(mesto);
        }
    }
}
