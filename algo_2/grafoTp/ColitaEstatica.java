package grafoTp;

public class ColitaEstatica implements Colita {
	private Object Vec[];
	private int Contador;
	private int Frente;
	private int Fondo;

	public ColitaEstatica(int n) {
		Vec = new Object[n];
		Contador = 0;
		Frente = 0;
		Fondo = n - 1;
	}

	public void Agregar(Object x) {
		if (Contador < Vec.length) {
			if (Fondo == Vec.length - 1)
				Fondo = 0;
			else
				Fondo++;
			Vec[Fondo] = x;
			Contador++;
		}
	}

	public void Sacar() {
		if (Frente == Vec.length - 1)
			Frente = 0;
		else
			Frente++;
		Contador--;
	}

	public Object VerFrente() {
		return Vec[Frente];
	}

	public boolean EsVacia() {
		return Contador == 0;
	}

}
