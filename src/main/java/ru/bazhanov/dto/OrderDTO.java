package ru.bazhanov.dto;

import java.time.LocalDate;

public class OrderDTO {
    private int toolId;
    private LocalDate startDate;
    private LocalDate stopDate;
    public OrderDTO(){}

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }
}
