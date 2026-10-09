package oncall.domain;

public enum Day {

    월요일("월"),
    화요일("화"),
    수요일("수"),
    목요일("목"),
    금요일("금"),
    토요일("토"),
    일요일("일");

    private final String name;

    Day(String name) {
        this.name = name;
    }

    public static Day from(String name) {
        for (Day day : values()) {
            if (day.name.equals(name)) {
                return day;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 요일입니다.");
    }

    public String takeName() {
        return name;
    }

    public Day next() {
        Day[] days = values();
        for (int i = 0; i < days.length; i++) {
            if (days[i] == this) {
                return days[(i+1)%days.length];
            }
        }
        throw new IllegalStateException("[ERROR] 존재하지 않는 요일입니다.");
    }


    public boolean isWeekend() {
        return this == 토요일 || this == 일요일;
    }
}
