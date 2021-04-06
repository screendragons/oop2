package practicumopdracht.models;

import java.io.Serializable;

/**
 * Functionality: Detail model
 *
 * @author Chi Yu Yeung
 */
public class Specification implements Serializable {
    private transient Smartphone hoortBij;
    private double inch;
    private double height;
    private double width;
    private double thickness;
    private boolean fingerprintSensor;
    private Object operatingSystem;
    private String note;

    /**
     * Constructor to define all the information
     *
     * @param hoortBij          belongs to which master
     * @param inch              the amount of inches
     * @param height            the height
     * @param width             the width
     * @param thickness         the thickness
     * @param fingerprintSensor the fingerprint sensor
     * @param operatingSystem   the operating system
     * @param note              the note
     */
    public Specification(Smartphone hoortBij, double inch, double height, double width, double thickness,
                         boolean fingerprintSensor, Object operatingSystem, String note) {
        this.hoortBij = hoortBij;
        this.inch = inch;
        this.height = height;
        this.width = width;
        this.thickness = thickness;
        this.fingerprintSensor = fingerprintSensor;
        this.operatingSystem = operatingSystem;
        this.note = note;
    }

    /**
     * The datatype of the hoortbij is Smartphone because the specification belongs to the smartphone
     *
     * @return
     */
    public Smartphone getHoortBij() {
        return hoortBij;
    }

    /**
     * Set the hoortbij so it can be edited
     *
     * @param hoortBij
     */
    public void setHoortBij(Smartphone hoortBij) {
        this.hoortBij = hoortBij;
    }

    /**
     * Get the inches
     *
     * @return
     */
    public double getInch() {
        return inch;
    }

    /**
     * Set the serie so it can be edited
     *
     * @param inch
     */
    public void setInch(double inch) {
        this.inch = inch;
    }

    /**
     * Get the height
     *
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the height so it can be edited
     *
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Get the width
     *
     * @return
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set the width so it can be edited
     *
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Get the thickness
     *
     * @return
     */
    public double getThickness() {
        return thickness;
    }

    /**
     * Set the thickness so it can be edited
     *
     * @param thickness
     */
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    /**
     * Get the fingerprint sensor
     *
     * @return
     */
    public boolean isFingerprintSensor() {
        return fingerprintSensor;
    }

    /**
     * Set the fingerprint so it can be edited
     *
     * @param fingerprintSensor
     */
    public void setFingerprintSensor(boolean fingerprintSensor) {
        this.fingerprintSensor = fingerprintSensor;
    }

    /**
     * Get the operating system
     *
     * @return
     */
    public Object getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * Set the operatingsystem so it can be edited
     *
     * @param operatingSystem
     */
    public void setOperatingSystem(Object operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * Get the note
     *
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     * Set the note so it can be edited
     *
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * The toString method shows the information
     *
     * @return
     */
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
