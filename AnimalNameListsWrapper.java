import java.util.List;
import java.util.ArrayList;

public class AnimalNameListsWrapper {
        private final List<String> hyenaNameList = new ArrayList<>();
        private final List<String> lionNameList = new ArrayList<>();
        private final List<String> tigerNameList = new ArrayList<>();
        private final List<String> bearNameList = new ArrayList<>();

        public List<String> getHyenaNameList() { return hyenaNameList; }
        public List<String> getLionNameList() { return lionNameList; }
        public List<String> getTigerNameList() { return tigerNameList; }
        public List<String> getBearNameList() { return bearNameList; }
    }
