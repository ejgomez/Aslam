package ejgomeznieto.model.database;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LECTO1")
public class Lectoc1 {
	
	private String annoProceso;
	private String mesProceso;
	private String diaProceso;
	private String annoYFecha;
	private String tipoRegistro;
	private String tipoCartera;
	private String fechaProceso;
	private String numeroEfecto;
	private String numeroVencimiento;
	private String vencimiento;
	private String importe;
	private String cajetin;
	private String primera;
	private String segunda;
	private String finalRegistro;
	private String totalNumeroEfectos;
	private String totalImporteEfectos;

}
