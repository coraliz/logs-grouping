import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    static final String OUTPUT_FILENAME_SUFFIX = "-grouped.txt";

    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Log>> logsGroupedByLength;
        var userInputFilePath = getUserInputFilePath();
        //to do: OPTIONAL
        //if userInputFilePath != null ?;
        try {
            TextFileLogsProvider textFileLogsProvider = new TextFileLogsProvider();
            LogsLengthGroupingLogic logsLengthGroupingLogic = new LogsLengthGroupingLogic();
            textFileLogsProvider.read(userInputFilePath, logsLengthGroupingLogic);
            logsGroupedByLength = logsLengthGroupingLogic.getLogsGroupedByLength();
            if(logsGroupedByLength.isEmpty()){
                System.out.println("\n" + userInputFilePath + " doesn't contain logs");
            }
            else{
                LogsPatternGroupingLogic logsPatternGroupingLogic = new LogsPatternGroupingLogic();
                logsPatternGroupingLogic.findAllPatterns(logsGroupedByLength);
                String outputFilePath = getOutputFilePath(userInputFilePath);
                TextFileGroupsLogsWriter textFileGroupsLogsWriter = new TextFileGroupsLogsWriter();
                textFileGroupsLogsWriter.write(outputFilePath, logsPatternGroupingLogic.getActivePatterns());
            }
        } catch (FileNotFoundException e) {
            System.out.println( "\n" + userInputFilePath + " doesn't exist.");
        } catch (IOException e) {
            System.out.println("\nSomething went wrong while accessing files or directories.\nPlease try again.");
        }
    }

    private static String getUserInputFilePath(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        return scanner.nextLine();
    }

    private static String getOutputFilePath(String inputFilePath){
        String[] splitOutputFilePath = inputFilePath.split("\\.", 2);
        return splitOutputFilePath[0] + OUTPUT_FILENAME_SUFFIX;
    }
}
