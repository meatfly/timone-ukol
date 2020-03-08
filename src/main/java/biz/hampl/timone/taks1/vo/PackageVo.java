package biz.hampl.timone.taks1.vo;

public class PackageVo {

    private final double weight;

    private final int postalCode;

    public PackageVo(double weight, int postalCode) {
        this.weight = weight;
        this.postalCode = postalCode;
    }

    public double getWeight() {
        return weight;
    }

    public int getPostalCode() {
        return postalCode;
    }
}
