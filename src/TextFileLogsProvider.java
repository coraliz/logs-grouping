
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextFileLogsProvider {
    public void read(String inputFilePath, LogsLengthGroupingLogic logsLengthGroupingLogic) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(inputFilePath))) {
            lines.filter(line -> !line.isEmpty()).forEach(logsLengthGroupingLogic::addLog);
        }
    }
}