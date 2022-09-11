import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InputHandler {
    private final static int MIN_LOG_LENGTH = 3;
    private final Map<Integer, List<String>> logsByLength;

    public InputHandler() {
        this.logsByLength = new HashMap<>();
    }

    public void read(String fileName) throws FileNotFoundException{
        File logFile = new File(fileName);
        if (logFile.isFile()) {
            Scanner reader = new Scanner(logFile);
            while (reader.hasNextLine()) {
                String logLine = reader.nextLine();
                int currentLogLineLength = (logLine.split(" ")).length;
                if (currentLogLineLength < MIN_LOG_LENGTH) continue;
                // create an hash map of <log string length, list of logs>
                if (!logsByLength.containsKey(currentLogLineLength)) {
                    logsByLength.put(currentLogLineLength, new ArrayList<>());
                }
                logsByLength.get(currentLogLineLength).add(logLine);
            }
            reader.close();
        }
        else {
            throw new FileNotFoundException();
        }
    }

    public Optional<Map<Integer, List<String>>> getLogs(){
        if (this.logsByLength.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.logsByLength);
    }
}