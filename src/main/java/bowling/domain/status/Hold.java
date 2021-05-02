package bowling.domain.status;

import bowling.domain.Pitch;

public class Hold extends Continue {

    private Pitch current;

    public Hold(Pitch current) {
        this.current = current;
    }

    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);

        if (current.isSpare(fallenPins)) {
            return new Spare(pitch);
        }

        return new Open(pitch);
    }

    @Override
    public boolean hasBonusPitch() {
        return false;
    }

    @Override
    public String display() {
        return String.valueOf(current.intValue());
    }
}
