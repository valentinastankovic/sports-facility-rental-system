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
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Recenzija;
import rs.ac.bg.fon.njt.NJTProjekat.repository.MyAppRepository;

/**
 *
 * @author AsusTuf
 */
@Repository
public class RecenzijaRepository implements MyAppRepository<Recenzija, Integer>{
@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Recenzija> findAll() {
        return entityManager.createQuery("SELECT r FROM Recenzija r", Recenzija.class ).getResultList();
    }

    @Override
    public Recenzija findById(Integer id) throws Exception {
        Recenzija recenzija = entityManager.find(Recenzija.class, id);
        if(recenzija == null){
            throw new Exception("Recenzija nije pronadjena!");
        }
        return recenzija;
    }

    @Override
    @Transactional
    public void save(Recenzija entity) {
        if(entity.getIdRecenzija()== null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
         Recenzija tipTerena = entityManager.find(Recenzija.class, id);
        if(tipTerena != null){
            entityManager.remove(tipTerena);
        }
    }
}
