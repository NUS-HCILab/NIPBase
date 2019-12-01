package com.hci.nip.base.actuator.model;


import com.hci.nip.base.model.BaseData;

/**
 * DO NOT CHANGE default values
 */
public class DisplayData implements BaseData {

    private String heading;
    private String subheading;
    private String content;
    private boolean html = false;
    private String image;
    private String audio;
    private String config = null;

    public DisplayData() {
    }

    public DisplayData(String heading, String subHeading, String content) {
        this.heading = heading;
        this.subheading = subHeading;
        this.content = content;
    }

    public DisplayData(DisplayData data) {
        this.heading = data.heading;
        this.subheading = data.subheading;
        this.content = data.content;
        this.html = data.html;
        this.image = data.image;
        this.audio = data.audio;
        this.config = data.config;
    }

    public String getHeading() {
        return heading;
    }

    public String getSubheading() {
        return subheading;
    }

    public String getContent() {
        return content;
    }

    public boolean isHtml() {
        return html;
    }

    public String getImage() {
        return image;
    }

    public String getAudio() {
        return audio;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "DisplayData{" +
                "heading='" + heading + '\'' +
                ", subheading='" + subheading + '\'' +
                ", content='" + content + '\'' +
                ", html=" + html +
                ", image='" + image + '\'' +
                ", audio='" + audio + '\'' +
                ", config='" + config + '\'' +
                '}';
    }
}
