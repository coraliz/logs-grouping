
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextFileLogsProvider {
    // todo: is this try over here ok? should I have written it differently?
    //todo check if logLine is empty line and skip it
    public void read(String inputFilePath, LogsLengthGroupingLogic logsLengthGroupingLogic) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(inputFilePath))) {
            //todo: any suggestion?
            lines.filter(line -> !line.equals("")).forEach(logsLengthGroupingLogic::addLog);
        }
    }
}