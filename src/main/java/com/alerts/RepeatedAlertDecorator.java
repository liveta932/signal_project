package com.alerts;

public class RepeatedAlertDecorator extends AlertDecorator {

    private int repeatCount;
    private long repeatIntervalMillis;

    public RepeatedAlertDecorator(Alert alert, int repeatCount, long repeatIntervalMillis) {
        super(alert);
        this.repeatCount = repeatCount;
        this.repeatIntervalMillis = repeatIntervalMillis;
    }

    @Override
    public String getCondition() {
        return alert.getCondition()  + " [Repeated alert: "  + repeatCount  + " times every "  + repeatIntervalMillis  + " ms]"; }
}