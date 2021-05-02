package bowling.domain.status;

import bowling.domain.BonusPitch;
import bowling.domain.Pitch;

public class Bonus implements Status {
    private static final int BONUS_PITCH_COUNT = 1;

    private final Pitch current;
    private final BonusPitch bonusPitch;

    public Bonus(Pitch current, int addedBonusCount, Status status) {
        this.current = current;
        this.bonusPitch = new BonusPitch(addedBonusCount, status);
    }

    @Override
    public Status roll(int fallenPins) {
        Pitch pitch = new Pitch(fallenPins);
        if (pitch.isStrike()) {
            return new Bonus(pitch, BONUS_PITCH_COUNT, new Strike());
        }

        if (current.isSpare(fallenPins)) {
            return new Bonus(pitch, BONUS_PITCH_COUNT, new Spare(pitch));
        }

        return new Bonus(pitch, BONUS_PITCH_COUNT, new Hold(pitch));
    }

    @Override
    public boolean isEnd() {
        return !bonusPitch.isAbleToPitch();
    }

    @Override
    public boolean hasBonusPitch() {
        return false;
    }

    @Override
    public String display() {
        return bonusPitch.display();
    }
}
