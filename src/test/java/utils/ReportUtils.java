package utils;

import org.testng.ITestResult;
import org.testng.ITestContext;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportUtils {



    private final static String H_LINE = "======================================================================\n";
    public final static String END_LINE = "\n___________________________________________________________________";

    private static String getCurrentDateTime() {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd, hh:mma");
        return dateFormat.format(date);

     }

     private static String getTestStatus(ITestResult result) {

        int status = result.getStatus();

        switch (status) {
            case 1:
                return "\u001B[32m" + "PASS" + "\u001B[0m";
            case 2:
                return "\u001B[31m" + "FAIL" + "\u001B[0m";
            default:
                return "UNDEFINED";

        }
     }

     private static String getTestRunTime(ITestResult result) {

        final long time = result.getEndMillis() - result.getStartMillis();
        int minutes = (int) ((time / 1000) / 60);
        int seconds = (int) ((time / 1000) % 60);
        return "" + minutes + " min " + seconds + " sec";

     }

     public static String getReportHeader(ITestContext context) {

        String header = "\tTest Report\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\n";
        String currentDate = "\tDate: " + getCurrentDateTime() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\n";
        String projectName = "\tProject OpenWeatherJava" + "\n";
        String baseUrl = "\tBASE_URL: " + TestUtils.getBaseUrl() + "\t\t\t\t\t\t\t\t\t\t\t" + "\n";
        return H_LINE + header + currentDate + projectName + baseUrl + H_LINE;

     }

     public static String getClassNameTestName(Method method, ITestResult result) {

        String className = result.getTestClass().toString();
        String testName = method.getName();
        return className.substring(22, className.length() - 1) + "/" + testName;

     }

     public static String getClassName(ITestResult result) {

         String className = result.getTestClass().toString();
         return className.substring(22, className.length() - 1);

     }

     public static String getTestStatistics(Method method, ITestResult result) {

        return getClassNameTestName(method, result)
                + "   ----   " + getTestStatus(result)
                + "\t Run Time: " + getTestRunTime(result)
                + END_LINE;

     }
}
