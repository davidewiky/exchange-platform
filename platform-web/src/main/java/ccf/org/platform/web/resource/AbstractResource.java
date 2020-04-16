package ccf.org.platform.web.resource;

import ccf.org.platform.model.common.AbstractEntity;
import ccf.org.platform.web.dto.AbstractDTO;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class AbstractResource<E extends AbstractEntity, T extends AbstractDTO> {

    private static final String DEFAULT_MAPPING_ID = "def";
    protected static final String B_MAPPING_ID = "B";
    protected static final String C_MAPPING_ID = "C";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Base method Dozer mapping from entity list to DTO list
    protected List<T> mapEntity(final List<E> entityList, final Class<T> tranferClass, final String mappingFile) {
        return mapEntity(entityList, tranferClass, mappingFile, Optional.empty());
    }

    // Overloaded method Dozer mapping from entity list to DTO list
    protected List<T> mapEntity(final List<E> entityList, final Class<T> tranferClass, final String mappingFile,
                                final Optional<String> id) {
        final List<T> tranferList = new ArrayList<>();
        final DozerBeanMapper mapper = new DozerBeanMapper(getMappingFiles(mappingFile));
        entityList.forEach(q -> tranferList.add(mapper.map(q, tranferClass, id.orElse(DEFAULT_MAPPING_ID))));

        return tranferList;
    }

    // Base method Dozer mapping from entity to DTO
    protected T mapEntity(final E entity, final Class<T> tranferClass, final String mappingFile) {
        return mapEntity(entity, tranferClass, mappingFile, Optional.empty());
    }

    // Overloaded method Dozer mapping from entity to DTO
    protected T mapEntity(final E entity, final Class<T> tranferClass, final String mappingFile,
                          final Optional<String> id) {
        final DozerBeanMapper mapper = new DozerBeanMapper(getMappingFiles(mappingFile));
        return mapper.map(entity, tranferClass, id.orElse(DEFAULT_MAPPING_ID));
    }

    // Base method Dozer mapping from DTO to entity
    protected E mapTransfer(final T transfer, final Class<E> entityClass, final String mappingFile) {
        return mapTransfer(transfer, entityClass, mappingFile, Optional.empty());
    }

    // Overloaded method Dozer mapping from DTO to entity
    protected E mapTransfer(final T transfer, final Class<E> entityClass, final String mappingFile,
                            final Optional<String> id) {
        final DozerBeanMapper mapper = new DozerBeanMapper(getMappingFiles(mappingFile));
        return mapper.map(transfer, entityClass, id.orElse(DEFAULT_MAPPING_ID));
    }

    // Base method Dozer mapping from DTO list to entity list
    protected List<E> mapTransfer(final List<T> transferList, final Class<E> entityClass, final String mappingFile) {
        return mapTransfer(transferList, entityClass, mappingFile, Optional.empty());
    }

    // Overloaded method Dozer mapping from DTO list to entity list
    protected List<E> mapTransfer(final List<T> transferList, final Class<E> entityClass, final String mappingFile,
                                  final Optional<String> id) {
        final List<E> entityList = new ArrayList<>();
        final DozerBeanMapper mapper = new DozerBeanMapper(getMappingFiles(mappingFile));
        transferList.forEach(q -> entityList.add(mapper.map(q, entityClass, id.orElse(DEFAULT_MAPPING_ID))));
        return entityList;
    }

    private List<String> getMappingFiles(final String ... mappingFile) {
        final List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozerJdk8Converters.xml");
        mappingFiles.addAll(Arrays.asList(mappingFile));

        return mappingFiles;
    }

}
