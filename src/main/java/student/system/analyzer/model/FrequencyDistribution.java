package student.system.analyzer.model;

import java.util.Objects;

public class FrequencyDistribution {

    private final String userId;
    private final int absoluteFrequency;
    private final double relativeFrequency;

    public FrequencyDistribution(String userId, int absoluteFrequency, double relativeFrequency) {
        this.userId = userId;
        this.absoluteFrequency = absoluteFrequency;
        this.relativeFrequency = relativeFrequency;
    }

    public String getUserId() {
        return userId;
    }

    public int getAbsoluteFrequency() {
        return absoluteFrequency;
    }

    public double getRelativeFrequency() {
        return relativeFrequency;
    }

    @Override
    public String toString() {
        return "FrequencyDistribution{" +
                "userId='" + userId + '\'' +
                ", absoluteFrequency=" + absoluteFrequency +
                ", relativeFrequency=" + relativeFrequency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FrequencyDistribution)) return false;
        FrequencyDistribution that = (FrequencyDistribution) o;
        return absoluteFrequency == that.absoluteFrequency && Double.compare(that.relativeFrequency, relativeFrequency) == 0 && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, absoluteFrequency, relativeFrequency);
    }
}
