package pl.edu.pja.prz.receivables.model;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
public class TransactionMapping extends BaseEntityLong implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(TransactionMapping.class);

    @Column(unique = true)
    @NotNull
    private String title;
    @Type(type = "uuid-char")
    @Column(length = 36)
    @NotNull
    private UUID childId;
    @Type(type = "uuid-char")
    @Column(length = 36)
    @NotNull
    private UUID guardianId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getChildId() {
        return childId;
    }

    public void setChildId(UUID childId) {
        this.childId = childId;
    }

    public UUID getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(UUID guardianId) {
        this.guardianId = guardianId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionMapping mapping = (TransactionMapping) o;
        return title.equals(mapping.title) &&
                childId.equals(mapping.childId) &&
                guardianId.equals(mapping.guardianId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, childId, guardianId);
    }

    @Override
    public String toString() {
        return childId + " - " + title;
    }

    @PostPersist
    public void postPersist() {
        logger.info("Saved transaction mapping: {}", this);
    }
}
