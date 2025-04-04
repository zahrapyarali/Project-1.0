/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;
import java.util.List;

/**
 *
 * @author Dell
 */
   public interface DAO<T> {
    void insert(T obj);
    void update(T obj);
    void delete(int id);
    List<T> findAll();
    
    Object findById(int id);
}
 

