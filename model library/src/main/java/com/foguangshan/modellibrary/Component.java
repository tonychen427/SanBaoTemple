package com.foguangshan.modellibrary;

/**
 * Created by MacBookPro on 3/30/15.
 */

public class Component {

    public enum ComponentType {
        ImageView, TextView, YouTube
    }
    public int id;
    public ComponentType componentType;
    public String value;
    public String additionalInfo;


    public Component() {
    }

    public Component(int id, ComponentType componentType, String value, String additionalInfo) {
        this.id = id;
        this.componentType = componentType;
        this.value = value;
        this.additionalInfo = additionalInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
