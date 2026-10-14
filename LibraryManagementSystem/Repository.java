import java.util.List;

public interface Repository<T> {

    void add(T item);

    boolean remove(T item);

    List<T> findAll();
}