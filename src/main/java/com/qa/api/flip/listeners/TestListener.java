package com.qa.api.flip.listeners;

import org.testng.ITestListener;

public class TestListener implements ITestListener {

    /*
     * Listener class which is implementing ITestListener and hence we can use this to dynamically create reports, write logs.
     */

    private static String TestcaseName;

    public static String getTestcaseName() {
        return TestcaseName;
    }

    public static void setTestcaseName(String testcaseName) {
        TestcaseName = testcaseName;
    }









}
