
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

public class NumbersThread extends Thread {
    private HashMap<Integer, Integer> map;
    private List<Pair<Integer, Integer> > list;

    public NumbersThread(HashMap<Integer, Integer> map, List<Pair<Integer, Integer> > list) {
        this.map = map;
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (map) {
            for (int i = 0; i < 1000000; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ThreadLocalRandom random = ThreadLocalRandom.current();
                int temp = random.nextInt(1000);
//                System.out.println(temp);
                if (map.containsKey(temp)) {
                    int valueOld = map.get(temp);
                    valueOld++;
                    list.add(new Pair<>(temp, valueOld));
                    map.put(temp, valueOld);
                } else {
                    map.put(temp, 1);
                    list.add(new Pair<>(temp, 1));
                }
            }
        }
    }
}
