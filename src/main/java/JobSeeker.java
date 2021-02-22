import java.util.Date;
import java.util.List;

public class JobSeeker extends User {

    /**
     * Поле Желаемая должность
     */
    private String expectedPosition;

    /**
     * Поле Ожидаемая зарплата
     */
    private int expectedSalary;

    /**
     * Поле Занятость
     */
    // todo можно сделать enum { Полная занятость, Частичная занятость,...}
    private String employment;

    /**
     * Поле График работы
     */
    // todo можно сделать enum { Полный день, Удаленная работа,... }
    private String workSchedule;

    /**
     * Поле Список навыков
     */
    private List<Skill> skills;

    /**
     * Поле дата начала стажа работы
     */
    private Date workingFromDate;
}
