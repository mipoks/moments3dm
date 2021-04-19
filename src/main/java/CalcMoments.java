
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;

public class CalcMoments extends Thread {
    private HashMap<Integer, Integer> map;
    private List<Moment> momentList;
    private List<Pair<Integer, Integer>> list;
    private final int ELEM_COUNT = 1000000;

    public CalcMoments(HashMap<Integer, Integer> map, List<Pair<Integer, Integer>> list) {
        this.map = map;
        momentList = new ArrayList<>();
        this.list = list;
    }

    public List<Moment> getMomentList() {
        return momentList;
    }

    private List<Integer> getAllX(int numX, int N) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<Integer> set = new LinkedHashSet<>();
        List<Integer> allX = new ArrayList<>();
        //arraylist
        // 7 6 25 8 25 81 8 8 652 64
        // 1 1 1  1  2  1 2 3  1  1

        //hashmap
        // 7 6 25 8 81 652 64
        // 1 1 2  3  1  1   1
        while (set.size() < numX) {
            int rand = random.nextInt(N);
            int temp = list.get(rand).getKey();
            if (!set.contains(temp)) {
                set.add(rand);

                int value = list.get(rand).getValue();
                allX.add(map.get(temp) - value + 1);
            }
        }
        return allX;
    }
    @Override
    public void run() {
        synchronized (map) {
            System.out.println("SIZE OF MAP " + map.size());
            System.out.println(map);
            long moment0 = 0;
            long moment1 = 0;
            Collection<Integer> values = map.values();
            Iterator<Integer> it = values.iterator();
            while(it.hasNext()) {
                Integer value = it.next();
                moment0 += Math.pow((double) value, 0);
                moment1 += Math.pow((double) value, 1);
            }
            momentList.add(new Moment(0, moment0));
            momentList.add(new Moment(1, moment1));

            //Calculate 2-nd moment based on the “Alon-Matias-Szegedy” Algorithm
            List<Integer> allX = getAllX(100, ELEM_COUNT); //with 100 unique variables
            it = allX.iterator();
            long sum = 0;
            while(it.hasNext()) {
                Integer x = it.next();
                sum = sum + ELEM_COUNT * (2 * x - 1);
            }
            momentList.add(new Moment(2, sum / 100.0, 100));

            //Calculate 2-nd moment based on the “Alon-Matias-Szegedy” Algorithm
            allX = getAllX(500, ELEM_COUNT); //with 500 unique variables
            it = allX.iterator();
            sum = 0;
            while(it.hasNext()) {
                Integer x = it.next();
                sum = sum + ELEM_COUNT * (2 * x - 1);
            }
            momentList.add(new Moment(2, sum / 500.0, 500));
        }
    }
}
