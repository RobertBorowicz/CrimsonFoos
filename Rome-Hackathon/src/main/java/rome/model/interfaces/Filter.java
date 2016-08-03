package rome.model.interfaces;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * An interface to filter a list.
 * 
 * @author Rome
 * @version 1.0
 * @since 7/26/2016
 *
 * @param <T> element type
 */
public interface Filter<T> {

	/**
	 * Returns a filtered list.
	 * 
	 * @param list list to be filtered
	 * @param tester the filtering test
	 * 
	 * @return a filtered list
	 */
	public default List<T> filterList(List<T> list, Predicate<T> tester) {
		return list.stream().filter(tester).collect(Collectors.toList());
	}
}
