public class Car {
    //attributes
    String id;
    String brand;
    String model;
    int horsePower;

    //toString method to display attributes in ListView
    @Override
    public String toString() {
        return "ID: " + id + ", Brand: " + brand + ", Model: " + model + ", HP: " + horsePower;
    }

    //constructor
    public Car(String id, String brand, String model, int horsePower) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
    }

    //getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getHorsePower() {
        return horsePower;
    }
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
