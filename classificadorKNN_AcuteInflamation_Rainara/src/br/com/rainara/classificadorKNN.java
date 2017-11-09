package br.com.rainara;

public class classificadorKNN {

	/**
	 * @param args
	 */
	private int qtdC1;
	private int qtdC2;
	private int acertosC1 = 0;
	private int acertosC2 = 0;
	private int atributos = 6;
	private int k = 10;
	
	
	
	
	public double[][] getVizinhos() {
		return vizinhos;
	}

	public void setVizinhos(double[][] vizinhos) {
		this.vizinhos = vizinhos;
	}

	private double vizinhos[][] = new double[k][2];

	public int getAtributos() {
		return atributos;
	}

	public void setAtributos(int atributos) {
		this.atributos = atributos;
	}

	public int getQtdC1() {
		return qtdC1;
	}

	public void setQtdC1(int qtdC1) {
		this.qtdC1 = qtdC1;
	}

	public int getQtdC2() {
		return qtdC2;
	}

	public void setQtdC2(int qtdC2) {
		this.qtdC2 = qtdC2;
	}


	public int getAcertosC1() {
		return acertosC1;
	}

	public void setAcertosC1(int acertosC1) {
		this.acertosC1 = acertosC1;
	}

	public int getAcertosC2() {
		return acertosC2;
	}

	public void setAcertosC2(int acertosC2) {
		this.acertosC2 = acertosC2;
	}

	
	public int getTotalAcertos() {
		return acertosC1+acertosC2;
	}

	// ------------- Função para normalizar -------------
	public void normalizar(double[][][] data) {
		double maior[] = { 0, 0, 0, 0, 0, 0, };
		double menor[] = { 100, 100, 100, 100 , 100, 100};

		int x = 0;
		// Encontra maior e menor valor de cada coluna
		for (int i = 0; i < atributos; i++) {
			for (int j = 0; j < data.length; j++) {
				if (data[j][0][i] > maior[x]) {
					maior[x] = data[j][0][i];
				}
				if (data[j][0][i] < menor[x]) {
					menor[x] = data[j][0][i];
				}
			}
			x++;
		}

		// Função de normalização
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < atributos; j++) {
				data[i][0][j] = ((data[i][0][j] - menor[j]) / (maior[j] - menor[j])) * 2 - 1;
				// System.out.print("  i:  " + i + " - " + data[i][0][j]);
			}
			// System.out.println("");
		}
	}

	public double[][] vizinho(double[][] distanciaEuclidiana, double[][][] data) {
		for (int x = 0; x < k; x++) {
			vizinhos[x][0] = 100;
			for (int j = 0; j < data.length; j++) {
				if (vizinhos[x][0] > distanciaEuclidiana[j][0]) {

					vizinhos[x][0] = distanciaEuclidiana[j][0];
					vizinhos[x][1] = distanciaEuclidiana[j][1];
					distanciaEuclidiana[j][0] = 1000;

				}

			}
			//System.out.println(vizinhos[x][0] + " - " + vizinhos[x][1]);

		}
		return vizinhos;

	}
	
	public void contaAcertos(double classe1,double classe2,double[][][] test,int i){
		if (classe1 > 0)
			classe1 = classe1 / qtdC1;
		else
			classe1 = 100;
		if (classe2 > 0)
			classe2 = classe2 / qtdC2;
		else
			classe2 = 100;
	

		if (classe1 < classe2 && test[i][1][0] == 0) {
			acertosC1++;
		}
		if (classe2 < classe1 && test[i][1][0] == 1) {
			acertosC2++;
		}
		
	}

	public void euclidiana(double[][] distanciaEuclidiana, double[][][] data,double[][][] test, int i) {

		double valor;
		qtdC1 = 0;
		qtdC2 = 0;


		for (int j = 0; j < data.length; j++) {
			valor = 0;
			distanciaEuclidiana[j][1] = data[j][1][0];
			for (int x = 0; x < atributos; x++) {
				valor += Math.pow((test[i][0][x] - data[j][0][x]), 2);
				// System.out.println( test[i][0][x] +" - "+
				// data[j][0][x]+"2 = "+valor);
				if (x == atributos - 1) {
					distanciaEuclidiana[j][0] = Math.sqrt(valor);
					System.out.println(distanciaEuclidiana[j][0] + " - "+ distanciaEuclidiana[j][1]);

				}
			}
		}

		System.out.println();
		vizinho(distanciaEuclidiana, data);

		double classe1 = 0, classe2 = 0;

		for (int x = 0; x < k; x++) {
			if (vizinhos[x][1] == 0) {
				qtdC1++;
				classe1 += vizinhos[x][0];

			}
			if (vizinhos[x][1] == 1) {
				qtdC2++;
				classe2 += vizinhos[x][0];

			}
			
		}
		
		contaAcertos(classe1,classe2,test,i);


	}

}
