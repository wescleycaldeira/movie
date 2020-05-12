package br.com.ais.base.entity;

import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;

public class AuditListener {

    @Inject
    private Principal principal;

    @PrePersist
    public void setCreatedOn(Auditable auditable) {
        AuditEntity audit = auditable.getAuditEntity();

        if(Objects.isNull(audit)) {
            audit = new AuditEntity();
            auditable.setAuditEntity(audit);
        }

        audit.setCreatedOn(LocalDateTime.now());
        audit.setCreatedBy(principal.getName());
    }

    @PreUpdate
    public void setUpdatedOn(Auditable auditable) {
        AuditEntity audit = auditable.getAuditEntity();

        audit.setUpdatedOn(LocalDateTime.now());
        audit.setUpdatedBy(principal.getName());
    }
}
