package com.umg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Familias {

    Scanner scanner = new Scanner(System.in);
    String rutaFamilias = System.getProperty("user.dir") + "/RegistroFamilias/familias.txt";
    
    public void menuPrincipal(){
        int opcion;
        
        do {
        
            System.out.println("***** MENU DEL SISTEMA *****");
            System.out.println("1. Agregar Familia");
            System.out.println("2. Buscar Familia");
            System.out.println("0. Salir del sistema");
            
            try {
                opcion = scanner.nextInt();
                
                switch(opcion) {
                    case 1: { ingresoFamilia(); break; }
                    case 2: { buscarFamilia(); break; }
                }
                
            } catch (Exception e) {
                opcion = 0;
            }
            
        } while(opcion != 0);
    }
    
    public void ingresoFamilia() {

        System.out.println("Ingrese el DPI del padre:");
        String dpiPadre = scanner.next();
        System.out.println("Ingrese el primer   nombre del padre:");
        String nombrePadre = scanner.next();
        System.out.println("Ingrese el primer apellido del padre:");
        String apellidoPadre = scanner.next();
        
        System.out.println("Ingrese el DPI de la madre:");
        String dpiMadre = scanner.next();
        System.out.println("Ingrese el primer nombre de la madre:");
        String nombreMadre = scanner.next();
        System.out.println("Ingrese el primer apellido de la madre");
        String apellidoMadre = scanner.next();

        System.out.println("Ingrese el nombre de su hijo");
        String nombreHijo = scanner.next();
        
        System.out.println("Ingrese el nombre de su hija");
        String nombreHija = scanner.next();
        
        FileWriter escritor = null;
        BufferedWriter bw = null;
        
        try {
            escritor = new FileWriter(rutaFamilias, true);
            bw = new BufferedWriter(escritor);
            
            String familia = dpiPadre + "," + dpiMadre + "," + nombrePadre + "," + apellidoPadre + "," + nombreMadre + "," + apellidoMadre +
                    "," + nombreHijo + "," + nombreHija;
            
            bw.write(familia);
            bw.newLine();
            
        } catch (Exception e) {
            System.out.println("Error al guardar el registro " + e.getMessage());
        } finally {
            try {
                bw.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el archivo " + e.getMessage());
            }
        }
        
    }
    
    public void buscarFamilia(){
        System.out.println("Ingrese el DPI del padre o la madre de familia para buscar la familia");
        String buscarDpi = scanner.next();
        
        FileReader lector = null;
        BufferedReader br = null;
        
        try {
            lector = new FileReader(rutaFamilias);
            br = new BufferedReader(lector);
            
            String linea;
            while((linea = br.readLine()) != null){
                
                String partesLinea[] = linea.split(",");
                
                String dpiTarget1 = partesLinea[0];
                String dpiTarget2 = partesLinea[1];
                
                if(dpiTarget1.equals(buscarDpi) || dpiTarget2.equals(buscarDpi)){
                    
                    String nombrePadre = partesLinea[2];
                    String nombreMadre = partesLinea[4];
                    String apellidoPadre = partesLinea[3];
                    String apellidoMadre = partesLinea[5];
                    String nombreHijo = partesLinea[6];
                    String nombreHija = partesLinea[7];
                    
                    System.out.println("Familia Encontrada:");
                    System.out.println("Familia: " + apellidoPadre + " " + apellidoMadre);
                    System.out.println("Padre de familia: " + nombrePadre + " " + apellidoPadre);
                    System.out.println("Madre de familia: " + nombreMadre + " " + apellidoMadre);
                    System.out.println("Hijo: " + nombreHijo + " " + apellidoPadre + " " + apellidoMadre);
                    System.out.println("Hija: " + nombreHija + " " + apellidoPadre + " " + apellidoMadre);
                }
                
            }
        } catch (Exception e) { 
            System.out.println("Error al buscar los campos" + e.getMessage());
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el archivo" + e.getMessage());

            }
        }
    }
}

