import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILENAME_SUFFIX = "-grouped.txt";

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String userInputFilePath;
        while ((userInputFilePath = getUserInputFilePath(scanner)).isEmpty());
        try {
            var textFileLogsProvider = new TextFileLogsProvider();
            var logsLengthGroupingLogic = new LogsLengthGroupingLogic();
            textFileLogsProvider.read(userInputFilePath, logsLengthGroupingLogic);
            var logsGroupedByLength = logsLengthGroupingLogic.getLogsGroupedByLength();
            if(logsGroupedByLength.isEmpty()){
                System.out.println("\n" + userInputFilePath + " doesn't contain logs");
            }
            else{
                var logsPatternGroupingLogic = new LogsPatternGroupingLogic();
                var outputFilePath = getOutputFilePath(userInputFilePath);
                var textFileGroupsLogsWriter = new TextFileGroupsLogsWriter();
                textFileGroupsLogsWriter.write(outputFilePath, logsPatternGroupingLogic.findAllPatterns(logsGroupedByLength));
            }
        } catch (FileNotFoundException e) {
            System.out.println( "\n" + userInputFilePath + " doesn't exist.");
        } catch (IOException e) {
            System.out.println("\nSomething went wrong while accessing files or directories.\nPlease try again.");
        }
    }

    private static String getUserInputFilePath(Scanner scanner){
        System.out.println("Enter file path: ");
        return scanner.nextLine();
    }

    private static String getOutputFilePath(String inputFilePath){
        var splitOutputFilePath = inputFilePath.split("\\.", 2);
        return splitOutputFilePath[0] + OUTPUT_FILENAME_SUFFIX;
    }
}
