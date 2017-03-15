package by.nahorny.taxipark.taxi;


/**
 * Created by Dmitri_Nahorny on 2/27/2017.
 */
public class PassengerTaxi extends Taxi{
    private int passengersCapacity;

    public PassengerTaxi (int boardId, CarBrand taxiBrand, float carPrice, int passengersCapacity) {
        super(boardId, taxiBrand, carPrice);
        this.passengersCapacity = passengersCapacity;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;

        PassengerTaxi other = (PassengerTaxi)obj;
        if(this.getBoardId() != other.getBoardId())
            return false;
        if(!this.getTaxiBrand().equals(other.getTaxiBrand()))
            return false;
        if(this.getCarPrice() != other.getCarPrice())
            return false;
        if(this.getPassengersCapacity() != other.getPassengersCapacity())
            return false;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder parametersBuffer =  new StringBuilder("Taxi type: Passenger Taxi").append(System.lineSeparator());
        parametersBuffer.append("Board ID: ").append(this.getBoardId()).append(System.lineSeparator());
        parametersBuffer.append("Car price: ").append(this.getCarPrice()).append(System.lineSeparator());
        parametersBuffer.append("Car brand: ").append(this.getTaxiBrand()).append(System.lineSeparator());

        parametersBuffer.append("Passengers capacity: ").append(this.getPassengersCapacity()).append(System.lineSeparator());

        for(int i = 0; i < 20 ; i++) {
            parametersBuffer.append("=");
        }
        parametersBuffer.append(System.lineSeparator());

        return parametersBuffer.toString();
    }
}
