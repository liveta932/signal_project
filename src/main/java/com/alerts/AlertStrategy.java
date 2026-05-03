package com.alerts;

import data_management.PatientRecord;
import java.util.List;

public interface AlertStrategy {

    List<Alert> checkAlert(List<PatientRecord> records);
}