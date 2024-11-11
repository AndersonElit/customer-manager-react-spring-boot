package customer.manager.application.mapper;

import java.lang.reflect.Field;
import java.util.List;

public class Mapper {

    public static <S,D> D map(S source, Class<D> destinationType) {

        try {
            D destination = destinationType.getDeclaredConstructor().newInstance();

            for (Field sourceField : source.getClass().getDeclaredFields()) {
                String fieldName = sourceField.getName();

                Field destinationField = destinationType.getDeclaredField(fieldName);

                sourceField.setAccessible(true);
                destinationField.setAccessible(true);

                if (sourceField.getType().equals(destinationField.getType())) {
                    destinationField.set(destination, sourceField.get(source));
                } else {
                    Object nestedSource = sourceField.get(source);
                    if (nestedSource != null) {
                        Object nestedDestination = map(nestedSource, destinationField.getType());
                        destinationField.set(destination, nestedDestination);
                    }
                }

            }

            return destination;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <S, D> List<D> mapAll(List<S> sourceList, Class<D> destinationType) {
        return sourceList.stream()
                .map(source -> map(source, destinationType))
                .toList();
    }
}
