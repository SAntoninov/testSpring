package pojos;

import java.util.List;

public class Output {

    private Float value;
    private List<Allocation> allocations;

    public Output(){
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }
}
