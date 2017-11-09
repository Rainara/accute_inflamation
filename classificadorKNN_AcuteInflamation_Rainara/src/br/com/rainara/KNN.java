package br.com.rainara;

import br.com.rainara.vo.Dados;

public class KNN extends Dados {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int verifica = 0;
		classificadorKNN iris = new classificadorKNN();

		iris.normalizar(data);
		iris.normalizar(test);

		int vetorSorteio[] = new int[test.length];

		int novovalor;
		int i;
		int z;

		for (i = 0; i < test.length; i++) {

			int valor = (int) (0 + Math.random() * 39); 
			vetorSorteio[i] = (int) valor; 
			novovalor = (int) (0 + Math.random() * 39);

			for (z = 0; z < test.length; z++) {

				if (vetorSorteio[z] == novovalor) {

					novovalor = (int) (0 + Math.random() * 39);
					z = 0;
				}

			}
			vetorSorteio[i] = novovalor;

			for (int x = vetorSorteio[i]; x < vetorSorteio[i]+1; x++) {
				iris.euclidiana(distanciaEuclidiana, data, test, x);

				System.out.println("Acertos Classe 1 = " + iris.getAcertosC1());
				System.out.println("Acertos Classe 2 = " + iris.getAcertosC2());
			}

			System.out.println("Total de Acertos = " + iris.getTotalAcertos());
		
		
		}

	}

}

