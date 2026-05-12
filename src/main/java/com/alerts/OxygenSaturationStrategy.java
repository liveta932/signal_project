package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy class for checking oxygen saturation alerts.
 */

public class OxygenSaturationStrategy implements AlertStrategy {

    private final LowSaturationRule lowSaturationRule;
    private final RapidSaturationDropRule rapidSaturationDropRule;
    private final HypotensiveHypoxemiaRule hypotensiveHypoxemiaRule;

    /**
     * Creates an OxygenSaturationStrategy with the needed oxygen rules.
     */

    public OxygenSaturationStrategy() {
        this.lowSaturationRule = new LowSaturationRule();
        this.rapidSaturationDropRule = new RapidSaturationDropRule();
        this.hypotensiveHypoxemiaRule = new HypotensiveHypoxemiaRule();
    }

    /**
     * Checks patient records for oxygen saturation alerts.
     * @param records the patient records to check
     * @return a list of oxygen saturation alerts
     */

    @Override
    public List<Alert> checkAlert(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();
        alerts.addAll(lowSaturationRule.check(records));
        alerts.addAll(rapidSaturationDropRule.check(records));
        alerts.addAll(hypotensiveHypoxemiaRule.check(records));
        return alerts;
    }
}