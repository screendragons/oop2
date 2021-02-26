package practicumopdracht.models;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public class Specification {
    private double inch;
    private double height;
    private double width;
    private double thickness;
    private boolean fingerprintSensor;
    private Object operatingSystem;
    private String note;

    public Specification(double inch, double height, double width, double thickness,
                         boolean fingerprintSensor, String operatingSystem, String note) {
        this.inch = inch;
        this.height = height;
        this.width = width;
        this.thickness = thickness;
        this.fingerprintSensor = fingerprintSensor;
        this.operatingSystem = operatingSystem;
        this.note = note;
    }

    public Specification(double inchField, double heightField, double widthField, double thicknessField,
                         Object operatingSystem) {
        this.inch = inchField;
        this.height = heightField;
        this.width = widthField;
        this.thickness = thicknessField;
        this.operatingSystem = operatingSystem;
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