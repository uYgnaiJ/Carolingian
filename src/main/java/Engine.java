import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Engine {
    public static final Engine E = new Engine();

    private final AtomicReference<LocalDate> date = new AtomicReference<>(LocalDate.of(1066, 1, 1));
    private final AtomicReference<Double> speed = new AtomicReference<>(1.0);
    private final AtomicReference<Boolean> pause = new AtomicReference<>(true);
    private final AtomicBoolean flag = new AtomicBoolean(true);
    private final Map<LocalDate, List<Event>> eventMap = new ConcurrentHashMap<>();

    private Engine(){}

    public void setSpeed(SpeedGear s){
        speed.set(s.value);
    }

    public enum SpeedGear{
        I(0.5),
        II(1),
        III(2),
        IV(5),
        V(1000),;

        private final double value;
        SpeedGear(double value){
            this.value = value;
        }
    }

    public void triggerPause(){
        pause.set(!pause.get());
    }

    public void quit(){
        flag.set(false);
    }

    public void addEvent(LocalDate date, Event event) {
        eventMap.computeIfAbsent(date, d -> new CopyOnWriteArrayList<>())
                .add(event);
    }

    public void start() throws ExecutionException, InterruptedException {
        var exec = Executors.newVirtualThreadPerTaskExecutor();
        Scanner scanner = new Scanner(System.in);

        Future<?> ticker = exec.submit(() -> {
            while (flag.get()) {
                if (pause.get()) {
                    try { Thread.sleep(50); } catch (InterruptedException ignored) {}
                    continue;
                }

                // handle today's events on new threads
                LocalDate now = date.get();
                List<Event> events = eventMap.get(now);
                if (events != null) {
                    for (Event event : events) {
                        exec.submit(event::run);
                    }
                }

                try {
                    Thread.sleep((long) (1000 / speed.get()));
                } catch (InterruptedException ignored) {}
                date.getAndUpdate(d->d.plusDays(1));
            }
        });

        Future<?> input = exec.submit(() -> {
            while (flag.get() && scanner.hasNextLine()) {
                String cmd = scanner.nextLine().trim().toLowerCase();
                switch (cmd) {
                    case "q" -> {
                        flag.set(false);
                        System.out.println("Ends on " + date);
                    }
                    case "p" -> {
                        System.out.println(date.get());
                        pause.set(!pause.get());
                    }
                    case "1" -> speed.set(0.5);
                    case "2" -> speed.set(1.0);
                    case "3" -> speed.set(2.0);
                    case "4" -> speed.set(5.0);
                    case "5" -> speed.set(1000.0);
                }

            }

        });

        System.out.println("Engine Started");
        ticker.get();
        input.get();

        scanner.close();
    }

}
