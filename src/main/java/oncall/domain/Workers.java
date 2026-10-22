package oncall.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Workers {
    private final List<Worker> workers;

    public Workers(List<String> names) {
        validatePossibleWorkersSize(names.size());
        validateNames(names);
        this.workers = names.stream()
                .map(name -> new Worker(name))
                .toList();
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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    public ArrayDeque<Worker> toQueue() {
        ArrayDeque<Worker> queue = new ArrayDeque<>(workers);
        return queue;
    }

    public Set<String> takeNames() {
        return workers.stream()
                .map(worker -> worker.takeName())
                .collect(Collectors.toSet());
    }
}
