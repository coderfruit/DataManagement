package com.stee.datamanagement.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "stl_device_alarms")
public class DeviceAlarmsEntity {
    private Integer deviceAlarmsId;
    private Date createTime;
    private String source;
    private Integer severityLevel;
    private String description;
    private String owner;
    private String category;
    private Integer tenantId;

    @Id
    @Column(name = "device_alarms_id")
    @SequenceGenerator(name = "device_alarms_seq_id", sequenceName = "device_alarms_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_alarms_seq_id")
    public Integer getDeviceAlarmsId() {
        return deviceAlarmsId;
    }

    public void setDeviceAlarmsId(Integer deviceAlarmsId) {
        this.deviceAlarmsId = deviceAlarmsId;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column(name = "severity_level")
    public Integer getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(Integer severityLevel) {
        this.severityLevel = severityLevel;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "tenant_id")
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

	@Override
	public String toString() {
		return "DeviceAlarmsEntity [deviceAlarmsId=" + deviceAlarmsId + ", createTime=" + createTime + ", source="
				+ source + ", severityLevel=" + severityLevel + ", description=" + description + ", owner=" + owner
				+ ", category=" + category + ", tenantId=" + tenantId + "]";
	}

}
