package restapi.models.resources.transformer;

import java.util.List;

/**
 * @author Felipe Sulzbach
 * @param <E> Conversion input.
 * @param <T> Conversion output.
 */
public interface Transformer<E, T> {

    T toTransform(E in);

    List<T> toTransform(List<E> inList);
}
