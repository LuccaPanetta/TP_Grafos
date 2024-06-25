package grafoTp;

import java.util.ArrayList;
import java.util.HashSet;

public class Grafitos {
	private int Mat[][];
	private Colita[] Lista;
	private int Costos[][];
	private HashSet<Viaje> Arbol;
	private char Letras[];
	private boolean Visitado[];

	public Grafitos() {
		int Mat[][] = {
				{ 0, 1, 0, 0, 0 },
				{ 1, 0, 0, 0, 1 },
				{ 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 0, 0 },
				{ 1, 0, 0, 1, 0 }
		};
		this.Mat = Mat;
		Lista = new Colita[Mat.length];
		for (int i = 0; i < Lista.length; i++)
			Lista[i] = new ColitaEstatica(Mat.length);
	}

	public Grafitos(int n) {
		Mat = new int[n][n];
		Lista = new Colita[n];
		for (int i = 0; i < Lista.length; i++)
			Lista[i] = new ColitaEstatica(Mat.length);
		Costos = new int[n][n];
	}

	public Grafitos(int Mat[][], int Costos[][], char nom[]) {
		this.Mat = Mat;
		Lista = new Colita[Mat.length];
		for (int i = 0; i < Lista.length; i++)
			Lista[i] = new ColitaEstatica(Mat.length);
		this.Costos = Costos;
		Arbol = new HashSet<>();
		Letras = nom;
	}

	public Grafitos(int Mat[][]) {
		this.Mat = Mat;
		Lista = new Colita[Mat.length];
		for (int i = 0; i < Lista.length; i++)
			Lista[i] = new ColitaEstatica(Mat.length);
		this.Visitado = new boolean[Mat.length];
	}

	public Grafitos Caminos(int t) {
		int b[][] = Mat;
		for (int i = 1; i < t; i++)
			b = Multiplicar(b, Mat);
		Grafitos g = new Grafitos(b);
		return g;
	}

	private static int[][] Multiplicar(int a[][], int b[][]) {
		int n = a.length;
		int c[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				c[i][j] = 0;
				for (int k = 0; k < n; k++) {
					int aux = a[i][k] * b[k][j];
					c[i][j] = c[i][j] + aux;
				}
			}
		}
		return c;
	}

	public static void MostrarGrafo(Grafitos g) {
		int m[][] = g.Mat;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void MostrarMatriz(boolean m[][]) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void Depth_First_Search(int k) {
		boolean Visitado[] = new boolean[Mat.length];
		for (int i = 0; i < Visitado.length; i++)
			Visitado[i] = false;
		Depth_First_Search(k, Visitado);
	}

	private void Depth_First_Search(int i, boolean v[]) {
		//Metodo de busqueda. Explorar lo más profundo posible a partir de cada nodo antes de retroceder.
		v[i] = true;
		System.out.print((i + 1) + " ");
		for (int k = 0; k < v.length; k++) {
			if (Mat[i][k] == 1 && !v[k]) {
				Depth_First_Search(k, v);
			}
		}
	}

	public void Breadth_First_Search(int v) {
		//Metodo de busqueda. Explora todos los vecinos de un nodo antes de pasar a los vecinos de esos vecinos, utilizando una cola.
		boolean Visitado[] = new boolean[Mat.length];
		for (int i = 0; i < Visitado.length; i++) {
			Visitado[i] = false;
		}
		Visitado[v] = true;
		Colita q = new ColitaEstatica(Visitado.length);
		q.Agregar(v);
		System.out.print((v + 1) + " ");
		while (!q.EsVacia()) {
			v = (int) q.VerFrente();
			q.Sacar();
			for (int j = 0; j < Mat.length; j++) {
				if (Mat[v][j] == 1) {
					if (!Visitado[j]) {
						q.Agregar(j);
						Visitado[j] = true;
						System.out.print((j + 1) + " ");
					}
				}
			}
		}
	}

	public boolean[][] Warshall() {
		//Buscar Conexiones. Encontrar las rutas más cortas entre todos los pares de vértices en un grafo.
		int n = Mat.length;
		boolean g[][] = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				g[i][j] = false;
				if (Mat[i][j] == 1)
					g[i][j] = true;
			}
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					g[i][j] = g[i][j] || (g[i][j] && g[k][j]);
				}
			}
		}
		return g;
	}

	public void Prim() {
		HashSet<Integer> uset = new HashSet<>();
		HashSet<Integer> vset = new HashSet<>();
		HashSet<Integer> vuset = new HashSet<>();
		for (int i = 0; i < Mat.length; i++)
			vset.add(i);
		for (int i = 0; i < Mat.length; i++)
			vuset.add(i);
		uset.add(0);
		int verticeU = 0;
		int verticeV = 0;
		vuset.remove(verticeU);
		while (!uset.equals(vset)) {
			int min = Integer.MAX_VALUE;
			int c = 0;
			Object u[] = uset.toArray();
			Object vu[] = vuset.toArray();
			for (int k = 0; k < u.length; k++) {
				for (int l = 0; l < vu.length; l++) {
					int uu = (int) u[k];
					int vv = (int) vu[l];
					c = Costos[uu][vv];
					if (c != 0) {
						if (min > c) {
							verticeU = uu;
							verticeV = vv;
							min = c;
						}
					}
				}
			}

			char Desde = Letras[verticeU];
			char Hasta = Letras[verticeV];
			Viaje a = new Viaje(Desde, Hasta, min);
			Arbol.add(a);
			uset.add(verticeV);
			vuset.remove(verticeV);
		}
	}

	public void MostrarPrim() {
		System.out.println(Arbol);
	}

	public int[][] GetMatrizAdyacencia() {
		return Mat;
	}

	public ArrayList<Integer> InstanciarArray() {
		return new ArrayList<>();
	}

	public boolean HallaCaminoNoIterativo(int origen, int llegada, ArrayList<Integer> a) {
		for (int i = 0; i < Visitado.length; i++) {
			this.Visitado[i] = false;
		}
		return HallaCamino(origen, llegada, a);
	}

	private boolean HallaCamino(int o, int l, ArrayList<Integer> a) {
		Visitado[o] = true;
		a.add(o);
		if (o == l) {
			return true;
		}
		//Recorrer nodos vecinos
		for (int k = 0; k < Visitado.length; k++) {
			if (Mat[o][k] == 1 && !Visitado[k]) {
				//Intentar encontrar un camino
				if (HallaCamino(k, l, a)) {
					return true;
				}
			}
		}
		Visitado[o] = false;
		a.remove(a.size() - 1);
		return false;
	}
	public void Grafo_Lista() {
		for (int i = 0; i < Lista.length; i++) {
			for (int j = 0; j < Lista.length; j++) {
				if (Mat[i][j] == 1) {
					Lista[i].Agregar((j + 1));
				}
			}
		}
	}
	
	public void MostrarLista() {
		Colita aux[] = Lista;
		for (int i = 0; i < Lista.length; i++) {
			System.out.print("adj a " + (i + 1) + ": ");
			while (!aux[i].EsVacia()) {
				System.out.print(aux[i].VerFrente() + " ");
				aux[i].Sacar();
			}
			System.out.println();
		}
	}
}
