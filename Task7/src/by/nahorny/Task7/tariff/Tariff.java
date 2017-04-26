package by.nahorny.Task7.tariff;

/**
 * Created by Dmitri_Nahorny on 3/22/2017.
 */
public class Tariff {

    private String tariffName;
    private String operatorName;
    private int payroll;
    private int withinNetworkCallPrice;
    private int outsideNetworkCallPrice;
    private int stationaryCallPrice;
    private int smsPrice;
    private int favouriteNumber;
    private String charging;
    private int subscriptionFee;

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getPayroll() {
        return payroll;
    }

    public void setPayroll(int payroll) {
        this.payroll = payroll;
    }

    public int getWithinNetworkCallPrice() {
        return withinNetworkCallPrice;
    }

    public void setWithinNetworkCallPrice(int withinNetworkCallPrice) {
        this.withinNetworkCallPrice = withinNetworkCallPrice;
    }

    public int getOutsideNetworkCallPrice() {
        return outsideNetworkCallPrice;
    }

    public void setOutsideNetworkCallPrice(int outsideNetworkCallPrice) {
        this.outsideNetworkCallPrice = outsideNetworkCallPrice;
    }

    public int getStationaryCallPrice() {
        return stationaryCallPrice;
    }

    public void setStationaryCallPrice(int stationaryCallPrice) {
        this.stationaryCallPrice = stationaryCallPrice;
    }

    public int getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(int smsPrice) {
        this.smsPrice = smsPrice;
    }

    public int getFavouriteNumber() {
        return favouriteNumber;
    }

    public void setFavouriteNumber(int favouriteNumber) {
        this.favouriteNumber = favouriteNumber;
    }

    public String getCharging() {
        return charging;
    }

    public void setCharging(String charging) {
        this.charging = charging;
    }

    public int getSubscriptionFee() {
        return subscriptionFee;
    }

    public void setSubscriptionFee(int subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }

    @Override
    public String toString() {
        StringBuilder tariffParameters = new StringBuilder();

        tariffParameters.append("Tariff Title: ").append(this.tariffName).append(System.lineSeparator());
        tariffParameters.append("Operator Name: ").append(this.operatorName).append(System.lineSeparator());
        tariffParameters.append("Total Payroll: ").append(this.payroll).append(System.lineSeparator());
        tariffParameters.append("Price for call within network: ").append(this.withinNetworkCallPrice).append(System.lineSeparator());
        tariffParameters.append("Price for call outside network: ").append(this.outsideNetworkCallPrice).append(System.lineSeparator());
        tariffParameters.append("Price for local network call: ").append(this.stationaryCallPrice).append(System.lineSeparator());
        tariffParameters.append("Price for SMS: ").append(this.smsPrice).append(System.lineSeparator());
        tariffParameters.append("Number of Favourite phone numbers: ").append(this.favouriteNumber).append(System.lineSeparator());
        tariffParameters.append("Charging type: ").append(this.charging).append(System.lineSeparator());
        tariffParameters.append("Subscription Fee: ").append(this.subscriptionFee).append(System.lineSeparator());

        for(int i = 0; i < 20; i++) {
            tariffParameters.append("=");
        }

        return tariffParameters.toString();
    }
}
