package oncall.domain;

public class Worker {
    private final String name;

    public Worker(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        boolean isInvalidLength = !(name.length() >= 1 && name.length() <= 5);
        if (isInvalidLength) {
            throw new IllegalArgumentException("[ERROR] 근무자 닉네임은 최대 5자입니다.");
        }
    }

}
