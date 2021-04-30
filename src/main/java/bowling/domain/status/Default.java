package bowling.domain.status;

import bowling.domain.Pitches;

public class Default extends Continue {
    @Override
    public boolean condition(Pitches pitches) {
        return false;
    }

    @Override
    public boolean conditionOf(int fallenPins, int accumulatedPins, int pitchIndex) {
        return false;
    }

    @Override
    public String display(Pitches pitches) {
        return "";
    }

    @Override
    public String display(int fallenPins) {
        return String.valueOf(fallenPins);
    }
}