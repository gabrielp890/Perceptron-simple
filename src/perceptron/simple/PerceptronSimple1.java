/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron.simple;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Gabriel
 */
public class PerceptronSimple1 {

    static float[][] entradas = {{170, 56, 1}, {172, 63, 0}, {160, 50, 1}, {170, 63, 0},
    {174, 66, 0}, {158, 55, 1}, {183, 80, 0}, {182, 70, 0}, {165, 54, 1}};
    float unbral = (float) 0.1;
    float[] pesos = new float[2];
    static float peso = 0.0f;
    float suma, err;
    int contador = 0;
    private float fActivacion;

    public PerceptronSimple1() {
        for (int i = 0; i < pesos.length; i++) {
            peso = (float) Math.random();
            String val = peso + "";
            BigDecimal big = new BigDecimal(val);
            big = big.setScale(2, RoundingMode.HALF_EVEN);
            peso = big.floatValue();
            pesos[i] = peso;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(entradas[i][j] + " - ");
            }
            System.out.println("");
        }
        for (int i = 0; i < pesos.length; i++) {
            System.out.println(pesos[i]);
        }
        for (int i = 0; i < entradas[0].length; i++) {
            System.out.println("Iteración " + contador + " :");
            fActivacion = entrenar(entradas[i]);
            System.out.println("Activacion: " + fActivacion);
            float error = error(entradas[i][2]);
            System.out.println("Error = " + error);
            if (error == 0) {
                System.out.println("--------------------------------");
                contador++;
            } else {
                calcularPesos(entradas[i], entradas[i][2]);
                i = -1;
                contador = 0;
            }
        }
        System.out.println("La red ya esta entrenada");
    }

    public float entrenar(float[] in) {
        for (int i = 0; i < in.length - 1; i++) {
            suma += pesos[i] * in[i];
            String val = suma + "";
            BigDecimal big = new BigDecimal(val);
            big = big.setScale(2, RoundingMode.HALF_UP);
            suma = big.floatValue();
            System.out.println("x" + i + " * w" + i);
            System.out.println(pesos[i] + " * " + in[i]);
        }
        System.out.println("suma = " + suma);
        if (suma >= unbral) {
            suma = 1;
        } else if (suma < unbral) {
            suma = -1;
        }
        return suma;
    }

    public float error(float salidaDeseada) {
        System.out.println("salida deseada - salida");
        err = salidaDeseada - fActivacion;
        System.out.println(salidaDeseada + " - " + fActivacion);
        return err;
    }

    public void calcularPesos(float[] in, float out) {
        if (err != 0) {
            if (err < 0) {
                for (int i = 0; i < in.length - 1; i++) {
                    System.out.println("x" + i + "= " + pesos[i] + " - 1");
                    pesos[i] = pesos[i] - 1;
                    String val = pesos[i] + "";
                    BigDecimal big = new BigDecimal(val);
                    big = big.setScale(2, RoundingMode.HALF_EVEN);
                    pesos[i] = big.floatValue();
                    fActivacion = big.floatValue();
                    System.out.println("ahora los pesos cambiaron a: " + pesos[i]);

                }
                System.out.println("umbral = " + unbral + " + 1");
                unbral++;
                System.out.println("el unbral cambio a= " + unbral);
            } else if (err > 0) {
                for (int i = 0; i < in.length - 1; i++) {
                    System.out.println("x" + i + "= " + pesos[i] + " + 1");
                    pesos[i] = pesos[i] + 1;
                    String val = pesos[i] + "";
                    BigDecimal big = new BigDecimal(val);
                    big = big.setScale(2, RoundingMode.HALF_EVEN);
                    fActivacion = big.floatValue();
                    System.out.println("ahora los pesos cambiaron a: " + pesos[i]);

                }
                System.out.println("umbral = " + unbral + " - 1");
                unbral--;
                System.out.println("el unbral cambio a= " + unbral);
            }
        }
    }

    public static void main(String[] args) {
        PerceptronSimple1 ps = new PerceptronSimple1();
    }

}
