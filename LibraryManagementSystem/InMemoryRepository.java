import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InMemoryRepository<T> implements Repository<T> {

    private List<T> items;
    private Function<T, String> idExtractor;

    public InMemoryRepository(Function<T, String> idExtractor) {
        this.items = new ArrayList<>();
        this.idExtractor = idExtractor;
    }

    @Override
    public void add(T item) {
        items.add(item);
    }

    @Override
    public boolean remove(T item) {
        return items.remove(item);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(items);
    }

    public T findById(String id) {

        for (T item : items) {

            if (idExtractor.apply(item).equalsIgnoreCase(id)) {
                return item;
            }
        }

        return null;
    }

    public boolean exists(String id) {
        return findById(id) != null;
    }
}