package org.house.projetjava8.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AutoAssignment {

    private final StringProperty personName;
    private final StringProperty bedLabel;
    private final StringProperty roomName;

    public AutoAssignment(String personName, String bedLabel, String roomName) {
        this.personName = new SimpleStringProperty(personName);
        this.bedLabel = new SimpleStringProperty(bedLabel);
        this.roomName = new SimpleStringProperty(roomName);
    }

    public String getPersonName() {
        return personName.get();
    }

    public StringProperty personNameProperty() {
        return personName;
    }

    public String getBedLabel() {
        return bedLabel.get();
    }

    public StringProperty bedLabelProperty() {
        return bedLabel;
    }

    public String getRoomName() {
        return roomName.get();
    }

    public StringProperty roomNameProperty() {
        return roomName;
    }
}
