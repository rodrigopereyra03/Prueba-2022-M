package test;

import java.time.LocalDate;
import java.time.LocalTime;

import modelo.Dispositivo;
import modelo.SistemaSaludDispositivos;

public class TestDePrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SistemaSaludDispositivos salud = new SistemaSaludDispositivos();
		
		
		
		System.out.println("TEST 1");
		
		salud.agregarEmpresa("Empresa 1");
		salud.agregarEmpresa("Empresa 2");
		
		System.out.println(salud);
		
		
		System.out.println("TEST 2");
		System.out.println(salud.traerEmpresa("Empresa 2"));
	
	
		System.out.println("TEST 3");
		try {
		salud.agregarDispositivos("Sensor Humedad", "A2020", salud.traerEmpresa("Empresa 1"));
		salud.agregarDispositivos("Sensor Temperatura", "A2325", salud.traerEmpresa("Empresa 1"));
		salud.agregarDispositivos("Sensor Presion", "B2021", salud.traerEmpresa("Empresa 2"));
		salud.agregarDispositivos("Sensor Calor", "B2326", salud.traerEmpresa("Empresa 2"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(salud.getLstDispositivos());
		
		
		System.out.println("TEST 4");
		try {
			salud.agregarDispositivos("Sensor Movimiento", "A2021", salud.traerEmpresa("Empresa 1"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("TEST 5");
		System.out.println(salud.traerDispositivo("B2326"));
		
		System.out.println("TEST 6");
		Dispositivo disp = salud.traerDispositivo("B2326");
		disp.agregarMetrica(18, LocalDate.of(2022, 9, 18), LocalTime.of(10, 0));
		disp.agregarMetrica(19, LocalDate.of(2022, 9, 19), LocalTime.of(12, 30));
		disp.agregarMetrica(23, LocalDate.of(2022, 9, 20), LocalTime.of(15, 0));
		disp.agregarMetrica(20, LocalDate.of(2022, 9, 21), LocalTime.of(18, 20));
		disp.agregarMetrica(18, LocalDate.of(2022, 9, 22), LocalTime.of(22, 30));
		
		System.out.println(disp);
		
		System.out.println("TEST 7");
		System.out.println(disp.traerMetrica(LocalDate.of(2022, 9, 19), LocalTime.of(12, 30)));
		
		System.out.println("TEST 8");
		System.out.println(disp.traerMetricas(LocalDate.of(2022, 9, 19), LocalDate.of(2022, 9, 21)));
			
		System.out.println("TEST 9");
		System.out.println(disp.traerMetricas(salud.traerDispositivo("B2326"), LocalDate.of(2022, 9, 19), LocalDate.of(2022, 9, 21), 22));
		
		
	}

}
