package com.myRoomie.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteMapXml {
    private String path;
    private String freq = "monthly";
    private String priority = "0.9";

    public SiteMapXml(String path, String freq, String priority) {
        this.path = path;
        this.freq = freq;
        this.priority = priority;
    }
}
