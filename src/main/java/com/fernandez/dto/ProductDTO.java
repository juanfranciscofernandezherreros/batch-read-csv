package com.fernandez.dto;

import java.math.BigDecimal;

public class ProductDTO {

    private String RiskGroup;
    private String Description;

    public String getRiskGroup() {
        return RiskGroup;
    }

    public void setRiskGroup(String riskGroup) {
        RiskGroup = riskGroup;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "RiskGroup='" + RiskGroup + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
