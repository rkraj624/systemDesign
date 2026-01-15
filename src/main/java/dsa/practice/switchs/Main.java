package dsa.practice.switchs;

public class Main {
    enum Days{
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    public static void main(String[] args) {
        Days friday = Days.FRIDAY;
        printDays(friday);
        System.out.println(getDays(Days.SUNDAY));
    }

    private static void printDays(Days friday) {
        switch (friday){
            case SATURDAY -> System.out.println("Today is Saturday");
            case FRIDAY -> System.out.println("Today is Friday");
            case SUNDAY -> System.out.println("Today is Sunday");
            case MONDAY -> System.out.println("Today is Monday");
            case TUESDAY -> System.out.println("Today is Tuesday");
            case WEDNESDAY -> System.out.println("Today is Wednesday");
            case THURSDAY -> System.out.println("Today is Thursday");
            default -> System.out.println("Today is a weekday");
        }
    }
    private static int getDays(Days day) {
        return switch (day){
            case SATURDAY -> {
                yield 6;
            }
            case MONDAY -> 1;
            case TUESDAY -> 2;
            case WEDNESDAY -> 3;
            case THURSDAY -> 4;
            case FRIDAY -> {
                yield 5;
            }

            case SUNDAY -> {
                if(day == Days.SUNDAY){
                    throw new RuntimeException("Sunday is holiday");
                }
                yield 0;
            }
        };
    }
}
