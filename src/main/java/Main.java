import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
        NumbersThread numbersThread = new NumbersThread(map, list);
        numbersThread.start();
        CalcMoments calcMoments = new CalcMoments(map, list);

        Thread thread = new Thread(calcMoments);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Moment> momentList = calcMoments.getMomentList();
        for (Moment moment : momentList) {
            System.out.println(moment);
        }
    }
}
