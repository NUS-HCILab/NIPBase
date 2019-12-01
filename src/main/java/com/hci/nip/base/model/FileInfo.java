package com.hci.nip.base.model;

public class FileInfo implements BaseData {
    private String src;
    /**
     * size is in bytes (to make kB divide by 1024)
     */
    private long size;

    public FileInfo() {
    }

    public FileInfo(String src) {
        this.src = src;
    }

    public FileInfo(String src, long size) {
        this.src = src;
        this.size = size;
    }

    public String getSrc() {
        return src;
    }

    /**
     * @return the size in bytes (0 for pipes)
     * NOTE: 1 KB = 1024 Bytes, 1 MB = 1024 KBytes
     */
    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "src='" + src + '\'' +
                ", size=" + size +
                '}';
    }
}
