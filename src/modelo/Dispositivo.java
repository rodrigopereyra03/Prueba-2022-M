package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Dispositivo {
	
	private int id;
	private String nombre;
	private String codigo;
	private List<Metrica> lstMetricas;
	private Empresa empresa;
	
	
	public Dispositivo(int id, String nombre, String codigo, Empresa empresa) throws Exception {
		super();
		this.id = id;
		this.nombre = nombre;
		this.setCodigo(codigo);
		this.lstMetricas = new ArrayList<>();
		this.empresa = empresa;
	}

	
	public boolean agregarMetrica(int valor, LocalDate fecha, LocalTime hora) {
		return lstMetricas.add(new Metrica(valor,fecha,hora));
	}
	public Metrica traerMetrica(LocalDate fecha,LocalTime hora) {
		boolean flag = false;
		Metrica metrica = null;
		int i = 0;
		while (i<lstMetricas.size()&&!flag) {
			Metrica mAux = lstMetricas.get(i);
			if(mAux.getFecha().equals(fecha) && mAux.getHora().equals(hora) ) {
				flag = true;
				metrica = lstMetricas.get(i);
			}
			i++;
		}
		return metrica;
	}
	
	public List<Metrica> traerMetricas(LocalDate desde, LocalDate hasta){
		List<Metrica> listM = new ArrayList<Metrica>();
		
		for(Metrica m : lstMetricas) {
			if((m.getFecha().isAfter(desde) || m.getFecha().equals(desde)) && (m.getFecha().isBefore(hasta) || m.getFecha().equals(hasta))) {
				listM.add(m);
			}
		}
		return listM;
	}
	
	public List<Metrica> traerMetricas(Dispositivo dispositivo,LocalDate desde, LocalDate hasta, int menorValor){
		List<Metrica> listM = new ArrayList<Metrica>();
		
		for(Metrica m: lstMetricas) {
			LocalDate fechaMetrica = m.getFecha();
			int valorMetrica = m.getValor();
			if((fechaMetrica.isAfter(desde) || fechaMetrica.equals(desde)) && (fechaMetrica.isBefore(hasta) || fechaMetrica.equals(hasta)) && valorMetrica < menorValor ) {
				listM.add(m);
			}
		}
		return listM;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo)throws Exception {
		codigo = codigo.toUpperCase();
		
		if(codigo.length() != 5) {
			throw new Exception("El codigo debe ser de 5 caracteres...");
		}
		
		if(!Funciones.esCadenaNros(codigo.substring(1))){
			throw new Exception("Los 4 ultimos digitos del codigo, deben ser numeros");
		}
		
		int acum=0;
		for(int i=1; i<codigo.length() ; i++) {
			acum = acum + Character.getNumericValue(codigo.charAt(i));
		}
		
		char firstChar = codigo.charAt(0);
		switch(firstChar) {
			case 'A':
					
				if(acum % 2 != 0) {
					throw new Exception("Cuando el codigo comienza con A, la suma de sus digitos debe ser par");
				}
				break;
				
			case 'B':
				if(acum % 2 == 0) {
					throw new Exception("Cuando el codigo comienza con B, la suma de sus digitos debe ser impar");
				}
				break;
				
			default:
					throw new Exception("El codigo debe comenzar con a, o con b");
			}
		
		this.codigo=codigo;
	}


	public List<Metrica> getLstMetricas() {
		return lstMetricas;
	}


	public void setLstMetricas(List<Metrica> lstMetricas) {
		this.lstMetricas = lstMetricas;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	@Override
	public String toString() {
		return "Dispositivo id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", lstMetricas=" + lstMetricas
				+ ", empresa=" + empresa + "\n";
	}
	
	
	
	

}
