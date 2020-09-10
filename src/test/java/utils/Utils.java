package utils;

import config.Config;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static String generateTimeStamp(){
        SimpleDateFormat format = new SimpleDateFormat(Config.getInstance().getProperty("timestamp"));
        return format.format(new Date());
    }

    //create screenshots directory

    private static void createDirs(String dirName){
        Path path = Paths.get(dirName);
        if (Files.exists(path)){
            System.out.printf("Directory already exists");
        } else {
            return;
        }

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String captureScreenshot(WebDriver driver, String testCaseName, boolean pass) throws IOException{
        String filename = testCaseName + "_" + generateTimeStamp() +".png";
        createDirs(Config.getInstance().getProperty("screenshot.pass.dir"));
        createDirs(Config.getInstance().getProperty("screenshot.fail.dir"));
        if (pass){
            Path fullName =Paths.get(Config.getInstance().getProperty("screenshot.pass.dir"), filename);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), fullName);
            return filename;
        } else {
            Path fullName =Paths.get(Config.getInstance().getProperty("screenshot.fail.dir"), filename);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), fullName);
            return filename;
        }
    }

}
