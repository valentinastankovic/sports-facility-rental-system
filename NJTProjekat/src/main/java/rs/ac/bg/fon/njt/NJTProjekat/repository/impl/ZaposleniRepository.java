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
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Zaposleni;
import rs.ac.bg.fon.njt.NJTProjekat.repository.MyAppRepository;

/**
 *
 * @author hallo
 */
@Repository
@Transactional
public class ZaposleniRepository implements MyAppRepository<Zaposleni, Integer>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Zaposleni> findAll() {
        return entityManager.createQuery("SELECT z FROM Zaposleni z", Zaposleni.class ).getResultList();
    }

    @Override
    public Zaposleni findById(Integer id) throws Exception {
        Zaposleni zaposleni = entityManager.find(Zaposleni.class, id);
        if(zaposleni == null){
            throw new Exception("Zaposleni nije pronadjen!");
        }
        return zaposleni;
    }

    @Override
    public void save(Zaposleni entity) {
        if(entity.getIdZaposleni() == null){
            entityManager.persist(entity);
        } else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
         Zaposleni zaposleni = entityManager.find(Zaposleni.class, id);
        if(zaposleni != null){
            entityManager.remove(zaposleni);
        }
    }
    
    public Zaposleni findByUsername(String username){
        List<Zaposleni> list = entityManager.createQuery("SELECT z FROM Zaposleni z WHERE z.username = :un", Zaposleni.class)
                .setParameter("un", username).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
    
    public boolean existsByUsername(String username){
        Integer c = entityManager.createQuery("SELECT COUNT(z) FROM Zaposleni z WHERE z.username = :un", Integer.class)
                .setParameter("un", username).getSingleResult();
        return c>0;
    }

    public boolean existsByEmail(String email){
        Integer c = entityManager.createQuery("SELECT COUNT(z) FROM Zaposleni z WHERE z.email = :em", Integer.class)
                .setParameter("em", email).getSingleResult();
        return c>0;
    }
}
