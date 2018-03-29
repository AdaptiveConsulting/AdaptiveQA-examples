package selenium.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.*;
import static selenium.DriverInit.getDriver;


public class ScreenCaptureListener extends TestListenerAdapter {

    private boolean createFile(File screenshot) {
        boolean fileCreated = false;

        if (screenshot.exists()) {
            fileCreated = true;
        } else {
            File parentDirectory = new File(screenshot.getParent());
            if (parentDirectory.exists() || parentDirectory.mkdirs()) {
                try {
                    fileCreated = screenshot.createNewFile();
                } catch (IOException errorCreatingScreenshot) {
                    errorCreatingScreenshot.printStackTrace();
                }
            }
        }

        return fileCreated;
    }

    private void writeScreenshotToFile(WebDriver driver, File screenshot) throws IOException {

        FileOutputStream screenshotStream = null;

        try {
            screenshotStream = new FileOutputStream(screenshot);
            screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            screenshotStream.close();
        }
        catch (IOException unableToWriteScreenshot) {
            err.println("Unable to write " + screenshot.getAbsolutePath());
            unableToWriteScreenshot.printStackTrace();
        }
        finally {
            screenshotStream.close();
        }
    }

    @Override
    public void onTestFailure(ITestResult failingTest) {
        try {
            WebDriver driver = getDriver();
            String screenshotDirectory = getProperty("screenshotDirectory", "target/screenshots");
            String screenshotAbsolutePath = screenshotDirectory + File.separator + currentTimeMillis() + "_" + failingTest.getName() + ".png";
            File screenshot = new File(screenshotAbsolutePath);
            if (createFile(screenshot)) {
                try {
                    writeScreenshotToFile(driver, screenshot);
                } catch (ClassCastException weNeedToAugmentOurDriverObject) {
                    writeScreenshotToFile(new Augmenter().augment(driver), screenshot);
                }
                out.println("Written screenshot to " + screenshotAbsolutePath);
            } else {
                err.println("Unable to create " + screenshotAbsolutePath);
            }
        } catch (Exception ex) {
            err.println("Unable to capture screenshot...");
            ex.printStackTrace();
        }
    }
}