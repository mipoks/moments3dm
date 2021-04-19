public class Moment {
    private int momentNumber;
    private double answer;
    private Integer variableCount = null;

    public Moment(int momentNumber, double answer, int variableCount) {
        this.momentNumber = momentNumber;
        this.answer = answer;
        this.variableCount = variableCount;
    }

    public Moment(int momentNumber, double answer) {
        this.momentNumber = momentNumber;
        this.answer = answer;
    }

    public int getMomentNumber() {
        return momentNumber;
    }

    public double getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return momentNumber + " moment: " +
                + answer +
                ", with " + variableCount +
                " variable (not null if used Alon-Matias-Szegedy Algorithm)";
    }
}
