abstract class Site {
    int capacity;
    String name;

    public Site(int capacity, String name){
        this.capacity = capacity;
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }
}
