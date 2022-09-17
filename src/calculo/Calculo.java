public class Calculo {
    private int subTotal;
    public Calculo(){

    }
    public int getSubTotal(int cant, int vUnit){
        subTotal = cant * vUnit;
        return subTotal;
    }
}