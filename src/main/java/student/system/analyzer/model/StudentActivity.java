package student.system.analyzer.model;

import java.util.Objects;

public class StudentActivity {

    private final String time;
    private final String eventContext;
    private final String component;
    private final String eventName;
    private final String description;

    public StudentActivity(String time, String eventContext, String component, String eventName, String description) {
        this.time = time;
        this.eventContext = eventContext;
        this.component = component;
        this.eventName = eventName;
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public String getEventContext() {
        return eventContext;
    }

    public String getComponent() {
        return component;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "student.system.analyzer.model.StudentActivity{" +
                "time=" + time +
                ", eventContext='" + eventContext + '\'' +
                ", component='" + component + '\'' +
                ", eventName='" + eventName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentActivity)) return false;
        StudentActivity that = (StudentActivity) o;
        return Objects.equals(time, that.time) && Objects.equals(eventContext, that.eventContext) && Objects.equals(component, that.component) && Objects.equals(eventName, that.eventName) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, eventContext, component, eventName, description);
    }
}
