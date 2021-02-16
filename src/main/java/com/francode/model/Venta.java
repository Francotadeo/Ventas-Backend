package com.francode.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "venta")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVentas;
	
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;
	
	@ManyToOne
	@JoinColumn(name="id_persona", nullable = false, foreignKey = @ForeignKey(name="FK_venta_persona"))
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name="id_producto", nullable = false, foreignKey = @ForeignKey(name="FK_venta_producto"))
	private Producto producto;
	
	@Column(name = "importe", nullable = false)
	private Double importe;
	
	@OneToMany(mappedBy = "venta", cascade= {CascadeType.ALL}, orphanRemoval = true)
	private List<DetalleVenta> detalleVenta;

	public Integer getIdVentas() {
		return idVentas;
	}

	public void setIdVentas(Integer idVentas) {
		this.idVentas = idVentas;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVentas == null) ? 0 : idVentas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		if (idVentas == null) {
			if (other.idVentas != null)
				return false;
		} else if (!idVentas.equals(other.idVentas))
			return false;
		return true;
	}

	
}
