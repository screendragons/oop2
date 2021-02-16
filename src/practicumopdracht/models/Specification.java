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
    private String operatingSystem;

    public Specification(double inch, double height, double width, double thickness, boolean fingerprintSensor,
                         String operatingSystem) {
        this.inch = inch;
        this.height = height;
        this.width = width;
        this.thickness = thickness;
        this.fingerprintSensor = fingerprintSensor;
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

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "inch=" + inch +
                ", height=" + height +
                ", width=" + width +
                ", thickness=" + thickness +
                ", fingerprintSensor=" + fingerprintSensor +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
}
