package br.com.ais.base.entity;

public interface Auditable {

    AuditEntity getAuditEntity();

    void setAuditEntity(AuditEntity auditEntity);
}
