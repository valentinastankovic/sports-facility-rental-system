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
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Iznajmljivanje;
import rs.ac.bg.fon.njt.NJTProjekat.repository.MyAppRepository;

/**
 *
 * @author AsusTuf
 */
@Repository
public class IznajmljivanjeRepository implements MyAppRepository<Iznajmljivanje, Integer>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Iznajmljivanje> findAll() {
        return entityManager.createQuery("SELECT i FROM Iznajmljivanje i", Iznajmljivanje.class ).getResultList();
    }

    @Override
    public Iznajmljivanje findById(Integer id) throws Exception {
        Iznajmljivanje iznajmljivanje = entityManager.find(Iznajmljivanje.class, id);
        if(iznajmljivanje == null){
            throw new Exception("Iznajmljivanje nije pronadjeno!");
        }
        return iznajmljivanje;
    }

    @Override
    @Transactional
    public void save(Iznajmljivanje entity) {
        if(entity.getIdIznajmljivanje()== null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
         Iznajmljivanje iznajmljivanje = entityManager.find(Iznajmljivanje.class, id);
        if(iznajmljivanje != null){
            entityManager.remove(iznajmljivanje);
        }
    }
}
