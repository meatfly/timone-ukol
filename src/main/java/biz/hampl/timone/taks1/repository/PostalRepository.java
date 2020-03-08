package biz.hampl.timone.taks1.repository;

import biz.hampl.timone.taks1.vo.PackageVo;
import java.util.ArrayList;
import java.util.List;

public class PostalRepository {

    private final List<PackageVo> data;

    public PostalRepository() {
        data = new ArrayList<>();
    }

    public void add(PackageVo packageVo) {
        data.add(packageVo);
    }

    public List<PackageVo> getAll() {
        return data;
    }
}
