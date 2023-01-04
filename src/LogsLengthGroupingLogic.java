import java.util.ArrayList;
import java.util.HashMap;

public class LogsLengthGroupingLogic {
    private final HashMap<Integer, ArrayList<Log>> _logsGroupedByLength;

    public LogsLengthGroupingLogic(){
        _logsGroupedByLength = new HashMap<>();
    }

    public void addLog(String logTextLine){
        var logLineSpaceCount = Math.toIntExact(logTextLine.chars().filter(ch -> ch == ' ').count());
        if (!_logsGroupedByLength.containsKey(logLineSpaceCount)){
            _logsGroupedByLength.put(logLineSpaceCount, new ArrayList<>());
        }
        _logsGroupedByLength.get(logLineSpaceCount).add(new Log(logTextLine));
    }

    public HashMap<Integer, ArrayList<Log>> getLogsGroupedByLength(){
        return _logsGroupedByLength;
    }

}
