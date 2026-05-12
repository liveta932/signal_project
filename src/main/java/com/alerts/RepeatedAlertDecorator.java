package com.alerts;

/**
 * Decorator for alerts that need repeated alert information.
 */

public class RepeatedAlertDecorator extends AlertDecorator {

    private int repeatCount;
    private long repeatIntervalMillis;

    public RepeatedAlertDecorator(Alert alert, int repeatCount, long repeatIntervalMillis) {
        super(alert);
        this.repeatCount = repeatCount;
        this.repeatIntervalMillis = repeatIntervalMillis;
    }

    /**
     * Gets the alert condition with repeated alert details.
     * @return the alert condition with repeat information
     */

    @Override
    public String getCondition() {
        return alert.getCondition()  + " [Repeated alert: "  + repeatCount  + " times every "  + repeatIntervalMillis  + " ms]"; }
}