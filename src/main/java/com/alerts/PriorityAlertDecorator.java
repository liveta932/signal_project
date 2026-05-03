package com.alerts;

public class PriorityAlertDecorator extends AlertDecorator {

    private final String priorityLevel;

    public PriorityAlertDecorator(Alert alert, String priorityLevel) {
        super(alert);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public String getCondition() { return "[Priority: " + priorityLevel + "] " + alert.getCondition(); }

}