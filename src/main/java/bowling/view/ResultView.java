package bowling.view;

import bowling.domain.*;
import bowling.domain.status.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static bowling.controller.BowlingGameController.MAX_FRAME_NO;
import static bowling.controller.BowlingGameController.START_FRAME_NO;

public class ResultView {
    private static final String BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    protected static final String DELIMITER = "|";
    protected static final String EMPTY_FRAME = "      |";
    protected static final String SYMBOL_GUTTER = "-";
    protected static final String ZERO = "0";

    public void initBoard(Player player) {
        printBoard(player, new Frames());
    }

    public void printBoard(Player player, Frames frames) {
        System.out.println(BOARD_HEADER);
        System.out.print(playerName(player.name()));
        System.out.println(frameResult(frames));
    }

    private String frameResult(Frames frames) {
        StringBuilder builder = new StringBuilder();

        IntStream.rangeClosed(START_FRAME_NO, MAX_FRAME_NO)
                .forEach(frameNo -> builder.append(framePitches(frameNo, frames)));

        return builder.toString();
    }

    private String framePitches(int frameNo, Frames frames) {
        Map<Integer, Frame> frameMap = frames.frames();
        if (frameMap.containsKey(frameNo)) {
            String frameResult = frameResult(frameMap.get(frameNo));
            return String.format(" %-3s  " + DELIMITER, frameResult);
        }
        return EMPTY_FRAME;
    }

    public String frameResult(Frame frame) {
        Pitches pitches = frame.pitches();
        return pitchesToString(pitches);
    }

    private String pitchesToString(Pitches pitches) {
        List<String> pitchesDisplay = new ArrayList<>();
        pitches.forEach(pitch -> pitchesDisplay.add(pitchToString(pitch)));
        return String.join("|", pitchesDisplay);
    }

    private String pitchToString(Pitch pitch) {
        Status status = pitch.status();
        return convertZeroToHyphen(status.display());
    }

    private String playerName(String name) {
        return String.format(DELIMITER + " %4s " + DELIMITER, name);
    }

    private String convertZeroToHyphen(String target) {
        return target.replace(ZERO, SYMBOL_GUTTER);
    }
}
