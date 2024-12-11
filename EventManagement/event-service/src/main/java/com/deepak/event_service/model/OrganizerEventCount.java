package com.deepak.event_service.model;

public class OrganizerEventCount {
    private long organizerId;
    private String organizerName;
    private int eventCount;

    public OrganizerEventCount(){
    }

    public OrganizerEventCount(long organizerId, String organizerName, int eventCount){
        this.organizerId = organizerId;
        this.organizerName = organizerName;
        this.eventCount = eventCount;
    }

    public long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(long organizerId) {
        this.organizerId = organizerId;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrganizerEventCount{");
        sb.append("organizerName='").append(organizerName).append('\'');
        sb.append(", eventCount=").append(eventCount);
        sb.append(", organizerId=").append(organizerId);
        sb.append('}');
        return sb.toString();
    }
}
