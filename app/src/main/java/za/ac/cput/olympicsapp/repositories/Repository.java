package za.ac.cput.olympicsapp.repositories;

import java.util.Set;

/**
 * Created by Lonwabo on 8/28/2016.
 */
public interface Repository<E,ID> {
    E findByID(ID id);
    E save(E entity);
    E update(E entity);
    E delete(E entity);
    Set<E> findAll();
    int deleteAll();
}
