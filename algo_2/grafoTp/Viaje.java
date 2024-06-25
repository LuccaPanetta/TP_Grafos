package grafoTp;

public class Viaje {
    private char Desde;
    private char Hasta;
    private int Costo;

    public Viaje(char D, char H, int C) {
        Desde = D;

        Hasta = H;

        Costo = C;
    }

    public char getDesde() {
        return Desde;
    }

    public char getHasta() {
        return Hasta;
    }

    public int getCosto() {
        return Costo;
    }

    public void setDesde(char desde) {
        Desde = desde;
    }

    public void setHasta(char hasta) {
        Hasta = hasta;
    }

    public void setCosto(int costo) {
        Costo = costo;
    }
    
}
