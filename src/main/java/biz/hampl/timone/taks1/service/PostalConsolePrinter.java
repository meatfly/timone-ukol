package biz.hampl.timone.taks1.service;

import biz.hampl.timone.taks1.repository.PostalRepository;
import biz.hampl.timone.taks1.vo.PackageVo;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class PostalConsolePrinter {

    private final PostalRepository postalRepository;

    ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

    public PostalConsolePrinter(PostalRepository postalRepository) {
        this.postalRepository = postalRepository;
    }

    public void init() {
        ses.scheduleWithFixedDelay(() -> printData(), 0, 1, TimeUnit.MINUTES);
    }

    private void printData() {
        Map<Integer, List<PackageVo>> map = postalRepository.getAll()
                .stream()
                .collect(Collectors.groupingBy(packageVo -> packageVo.getPostalCode()));

        map.entrySet()
                .stream()
                .map(entry -> {
                    List<PackageVo> packageVos = entry.getValue();
                    double sum = packageVos
                            .stream()
                            .mapToDouble(PackageVo::getWeight)
                            .sum();
                    return ImmutablePair.of(sum, entry.getKey());
                })
                .sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey()))
                .forEach(pair -> System.out.println(pair.getValue() + " " + pair.getKey()));
    }
}
