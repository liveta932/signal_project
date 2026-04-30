# Code Coverage Notes

The main required project functionality was tested. The `data_management` package reached 83% coverage, and the `com.alerts` package reached 100% coverage.

The total project coverage is 44% because the simulator, generator, and output packages were not fully tested. These classes depend on random data generation, current system time, file output, TCP output, and WebSocket output. Those parts would require integration tests or mocked output strategies, so I focused my unit tests on the required patient storage, file reading, and alert rule functionality.

## Screenshots

- Unit test verification: `submission_part3/screenshots/unit-tests-passed.png`
- Code coverage report: `submission_part3/screenshots/jacoco-coverage-report.png`