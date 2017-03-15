package by.nahorny.taxipark.taxi;

/**
 * Created by Dmitri_Nahorny on 2/27/2017.
 */
public abstract class Taxi{
    private int boardId;
    private CarBrand taxiBrand;
    private float carPrice;

    public Taxi(int boardId, CarBrand taxiBrand, float carPrice) {
        this.boardId = boardId;
        this.taxiBrand = taxiBrand;
        this.carPrice = carPrice;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public CarBrand getTaxiBrand() {
        return taxiBrand;
    }

    public void setTaxiBrand(CarBrand taxiBrand) {
        this.taxiBrand = taxiBrand;
    }

    public float getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(float carPrice) {
        this.carPrice = carPrice;
    }

}