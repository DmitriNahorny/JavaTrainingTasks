package by.nahorny.taxipark.taxi;

/**
 * Created by Dmitri_Nahorny on 2/27/2017.
 */
public class CargoTaxi extends Taxi {
    private float carryingCapacity;

    public CargoTaxi(int boardId, CarBrand taxiBrand, float carPrice, float carryingCapacity) {
        super(boardId, taxiBrand, carPrice);
        this.carryingCapacity = carryingCapacity;
    }

    public float getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(float carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;

        CargoTaxi other = (CargoTaxi)obj;
        if(this.getBoardId() != other.getBoardId())
            return false;
        if(!this.getTaxiBrand().equals(other.getTaxiBrand()))
            return false;
        if(this.getCarPrice() != other.getCarPrice())
            return false;
        if(this.getCarryingCapacity() != other.getCarryingCapacity())
            return false;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder parametersBuffer =  new StringBuilder("Taxi type: Cargo Taxi").append(System.lineSeparator());
        parametersBuffer.append("Board ID: ").append(this.getBoardId()).append(System.lineSeparator());
        parametersBuffer.append("Car price: ").append(this.getCarPrice()).append(System.lineSeparator());
        parametersBuffer.append("Car brand: ").append(this.getTaxiBrand()).append(System.lineSeparator());

        parametersBuffer.append("Carrying capacity: ").append(this.getCarryingCapacity()).append(System.lineSeparator());

        for(int i = 0; i < 20 ; i++) {
            parametersBuffer.append("=");
        }
        parametersBuffer.append(System.lineSeparator());

        return parametersBuffer.toString();
    }
}
