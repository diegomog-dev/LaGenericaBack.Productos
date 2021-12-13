package co.edu.unbosque.lagenericaGr38Eq5.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="productos")
public class Producto {
	
	//@Id
	private int codProducto;
	
	private String nomProducto;
	private int nitProveedor;
	private double precioCompra;
	private double ivaCompra;
	private double precioVenta;
	
	public Producto() {
		
	}

	public Producto(int codProducto, String nomProducto, int nitProveedor, double precioCompra, double ivaCompra,
			double precioVenta) {
		super();
		this.codProducto = codProducto;
		this.nomProducto = nomProducto;
		this.nitProveedor = nitProveedor;
		this.precioCompra = precioCompra;
		this.ivaCompra = ivaCompra;
		this.precioVenta = precioVenta;
	}

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	public String getNomProducto() {
		return nomProducto;
	}

	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}

	public int getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(int nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public double getIvaCompra() {
		return ivaCompra;
	}

	public void setIvaCompra(double ivaCompra) {
		this.ivaCompra = ivaCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	@Override
	public String toString() {
		return "Producto [codProducto=" + codProducto + ", nomProducto=" + nomProducto + ", nitProveedor="
				+ nitProveedor + ", precioCompra=" + precioCompra + ", ivaCompra=" + ivaCompra + ", precioVenta="
				+ precioVenta + "]";
	}
		
}
