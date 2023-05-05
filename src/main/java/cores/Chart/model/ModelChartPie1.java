package cores.Chart.model;

import java.awt.Color;

public class ModelChartPie1 {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public ModelChartPie1(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public ModelChartPie1(String name, double value, Color color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public ModelChartPie1() {
    }

    private String name;
    private double value;
    private Color color;
}
