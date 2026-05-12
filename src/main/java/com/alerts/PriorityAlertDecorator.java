package com.alerts;

/**
 * Decorator class that adds a priority level to an alert.
 */

public class PriorityAlertDecorator extends AlertDecorator {

    private final String priorityLevel;

    public PriorityAlertDecorator(Alert alert, String priorityLevel) {
        super(alert);
        this.priorityLevel = priorityLevel;
    }

    /**
     * Gets the alert condition with the priority level added.
     * @return the condition with priority information
     */

    @Override
    public String getCondition() { return "[Priority: " + priorityLevel + "] " + alert.getCondition(); }

}