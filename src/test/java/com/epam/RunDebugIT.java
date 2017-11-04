package com.epam;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.IOException;
import java.lang.management.ManagementFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/epam/steps",
        strict = true,
        plugin = {
                "pretty",
                "json:target/Cucumber.json",
                "html:target/cucumber-html-report"
        }
)
public class RunDebugIT {
    private static final String SUITE_NAME = "Debug";
    private static final Logger LOGGER = LoggerFactory.getLogger(RunDebugIT.class);

    @AfterClass
    public static void tearDown() throws IOException {
        logMemoryUsage();
        WebDriverPool.DEFAULT.dismissAll();
        LOGGER.info("FINISH SUITE " + SUITE_NAME + " *****************************************");
    }

    @BeforeClass
    public static void setup() throws IOException {
        logMemoryUsage();
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        LOGGER.info("START SUITE " + SUITE_NAME + " *****************************************");
    }

    public static void logMemoryUsage(){
        LOGGER.info("MEMORY USAGE:");
        LOGGER.info("Heap: " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
        LOGGER.info("NonHeap: " + ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage());
    }
}
