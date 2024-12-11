package com.deepak.organizer_service.model;

import java.util.Objects;

public class Organizer{

    private long organizerId;
    private String contactInfo;
    private long accountId;

    public Organizer(){
    }

    public Organizer(long organizerId, String contactInfo, long accountId){
        this.organizerId = organizerId;
        this.contactInfo = contactInfo;
        this.accountId = accountId;
    }

    public long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(long organizerId) {
        this.organizerId = organizerId;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Organizer{");
        sb.append("organizerId=").append(organizerId);
        sb.append(", contactInfo='").append(contactInfo).append('\'');
        sb.append(", accountId=").append(accountId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organizer organizer = (Organizer) o;
        return organizerId == organizer.organizerId && accountId == organizer.accountId && Objects.equals(contactInfo, organizer.contactInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizerId, contactInfo, accountId);
    }
}
