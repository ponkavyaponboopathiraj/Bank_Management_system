package src.repository.interfaces;
import java.util.List;

public interface RepoInterface<T> {
    boolean add(T obj);
    boolean update(T obj);
    boolean deleteById(int id);
    T getById(int id);
    List<T> getAll();
}

