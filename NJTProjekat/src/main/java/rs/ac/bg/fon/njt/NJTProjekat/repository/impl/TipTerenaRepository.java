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
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.TipTerena;
import rs.ac.bg.fon.njt.NJTProjekat.repository.MyAppRepository;

/**
 *
 * @author AsusTuf
 */
@Repository
public class TipTerenaRepository implements MyAppRepository<TipTerena, Integer>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TipTerena> findAll() {
        return entityManager.createQuery("SELECT t FROM TipTerena t", TipTerena.class ).getResultList();
    }

    @Override
    public TipTerena findById(Integer id) throws Exception {
        TipTerena tipTerena = entityManager.find(TipTerena.class, id);
        if(tipTerena == null){
            throw new Exception("Tip terena nije pronadjen!");
        }
        return tipTerena;
    }

    @Override
    @Transactional
    public void save(TipTerena entity) {
        if(entity.getIdTipTerena()== null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
         TipTerena tipTerena = entityManager.find(TipTerena.class, id);
        if(tipTerena != null){
            entityManager.remove(tipTerena);
        }
    }
}
