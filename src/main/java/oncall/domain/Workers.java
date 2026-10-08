package oncall.domain;

import java.util.*;

public class Workers {
    private final List<Worker> workers;

    public Workers(List<String> names) {
        validatePossibleWorkersSize(names.size());
        validateNames(names);
        this.workers = new ArrayList<>();
        for (String name : names) {
            Worker worker = new Worker(name);
            workers.add(worker);
        }
    }

    private void validatePossibleWorkersSize(int numbers) {
        boolean impossibleSize = !(numbers >= 5 && numbers <= 35);
        if (impossibleSize) {
            throw new IllegalArgumentException("[ERROR] 근무 가능한 인원은 최소 5명 이상 최대 35명 이하입니다.");
        }
    }

    private void validateNames(List<String> names) {
        Set<String> uniqueNames = new HashSet<>(names);
        boolean isDuplicated = uniqueNames.size() < names.size();
        if (isDuplicated) {
            throw new IllegalArgumentException("[ERROR] 같은 이름을 사용할 수 없습니다.");
        }
    }
}
