package interfaces;

import java.util.List;

public interface IFiltering<T> {
    List<T> filter(List<T> data);
}
