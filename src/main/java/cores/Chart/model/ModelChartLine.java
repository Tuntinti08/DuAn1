package cores.Chart.model;

import java.math.BigDecimal;

public class ModelChartLine {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public ModelChartLine(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public ModelChartLine() {
    }

    private String name;
    private BigDecimal value;
}
