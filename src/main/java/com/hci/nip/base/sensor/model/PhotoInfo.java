package com.hci.nip.base.sensor.model;

import com.hci.nip.base.model.BaseData;

/**
 * DO NOT MODIFY DEFAULT values
 */
public class PhotoInfo implements BaseData {
    private String id;
    private String dest;
    private String resolution;
    private long postViewMillis = 0;

    public PhotoInfo(String id) {
        this.id = id;
    }

    public PhotoInfo(PhotoInfo photoInfo) {
        this.id = photoInfo.id;
        this.dest = photoInfo.dest;
        this.resolution = photoInfo.resolution;
        this.postViewMillis = photoInfo.postViewMillis;
    }

    public String getId() {
        return id;
    }

    public String getDest() {
        return dest;
    }

    public String getResolution() {
        return resolution;
    }

    public long getPostViewMillis() {
        return postViewMillis;
    }

    public PhotoInfo setId(String id) {
        this.id = id;
        return this;
    }

    public PhotoInfo setDest(String dest) {
        this.dest = dest;
        return this;
    }

    public PhotoInfo setResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    public PhotoInfo setPostViewMillis(long postViewMillis) {
        this.postViewMillis = postViewMillis;
        return this;
    }

    @Override
    public String toString() {
        return "PhotoInfo{" +
                "dest='" + dest + '\'' +
                ", resolution='" + resolution + '\'' +
                ", postViewMillis=" + postViewMillis +
                '}';
    }

}
