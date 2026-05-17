package ModelSystem;

public class Asset {
    private String name;
    private double value;
    private String type;

    public Asset(String name, double value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }
} 
