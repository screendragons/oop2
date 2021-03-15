package practicumopdracht.models;

import java.io.Serializable;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class Specification implements Serializable {
    private int id;
    private static int uniqueID = 1;

    private double inch;
    private double height;
    private double width;
    private double thickness;
    private boolean fingerprintSensor;
    private Object operatingSystem;
    private String note;

    private int hoortBij;

    public Specification(double inch, double height, double width, double thickness,
                         boolean fingerprintSensor, String operatingSystem, String note, int hoortBij) {
        this.inch = inch;
        this.height = height;
        this.width = width;
        this.thickness = thickness;
        this.fingerprintSensor = fingerprintSensor;
        this.operatingSystem = operatingSystem;
        this.note = note;
        this.id = uniqueID++;
        this.hoortBij = hoortBij;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public boolean isFingerprintSensor() {
        return fingerprintSensor;
    }

    public void setFingerprintSensor(boolean fingerprintSensor) {
        this.fingerprintSensor = fingerprintSensor;
    }

    public Object getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getHoortBij() {
        return hoortBij;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format(
                "Inch: %.2f\nHeight: %.2f\nWidth: %.2f\nThickness: %.2f\nFingerprint identity sensor: %s\n" +
                        "Operatingsystem: %s\nNotes: %s",
                inch,
                height,
                width,
                thickness,
                fingerprintSensor ? "Yes" : "No",
                operatingSystem,
                note
        );
    }
}
