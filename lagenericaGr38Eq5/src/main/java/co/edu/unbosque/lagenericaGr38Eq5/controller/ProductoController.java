package co.edu.unbosque.lagenericaGr38Eq5.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.ConfigurationException;
import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import co.edu.unbosque.lagenericaGr38Eq5.model.CSVService;
import co.edu.unbosque.lagenericaGr38Eq5.model.Producto;
import co.edu.unbosque.lagenericaGr38Eq5.repository.ProductoRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductoController {
	

	
	@Autowired
	ProductoRepository productoRepository;
	
	@PostMapping("/db_productos")
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto _product = productoRepository.save(new Producto(producto.getCodProducto(), producto.getNomProducto(), producto.getNitProveedor(), producto.getPrecioCompra(), producto.getIvaCompra(), producto.getPrecioVenta()));
			return new ResponseEntity<>(_product, HttpStatus.CREATED);
			}catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	@GetMapping("/db_productos")
	  public ResponseEntity<List<Producto>> getAllProducto(@RequestParam(required = false) String nomProducto) {
		  try {
			    List<Producto> products = new ArrayList<Producto>();

			    if (nomProducto == null)
			      productoRepository.findAll().forEach(products::add);
			    else
			      productoRepository.findByNomProducto(nomProducto).forEach(products::add);

			    if (products.isEmpty()) {
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    }

			    return new ResponseEntity<>(products, HttpStatus.OK);
			  } catch (Exception e) {
			    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }
	
	  @DeleteMapping("/db_productos")
	  public ResponseEntity<HttpStatus> deleteAllProducto() {
		  try {
			    productoRepository.deleteAll();
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  } catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }
	  
	  @PutMapping("/db_productos/{codProducto}")
	  public ResponseEntity<Producto> updateProducto(@PathVariable("codProducto") int codProducto, @RequestBody Producto producto) {
		  Optional<Producto> productoData = productoRepository.findByCodProducto(codProducto);;

		  if (productoData.isPresent()) {
		    Producto _product = productoData.get();
		    _product.setCodProducto(producto.getCodProducto());
		    _product.setNomProducto(producto.getNomProducto());
		    _product.setNitProveedor(producto.getNitProveedor());
		    _product.setPrecioCompra(producto.getPrecioCompra());
		    _product.setIvaCompra(producto.getIvaCompra());
		    _product.setPrecioVenta(producto.getPrecioVenta());
		    	       
		    return new ResponseEntity<>(productoRepository.save(_product), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }
	  
	  @GetMapping("/db_productos/{codProducto}")
	  public ResponseEntity<Producto> getProductoByCodProducto(@PathVariable("codProducto") int codProducto) {
		  Optional<Producto> productoData = productoRepository.findByCodProducto(codProducto);

		  if (productoData.isPresent()) {
		    return new ResponseEntity<>(productoData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }
	  	
	  @Autowired
		CSVService Service; 
		
		@PostMapping(value="/uploadCSV", consumes = "multipart/form-data")
		public ResponseEntity<List<Producto>> uploadSingleCSVFile(@RequestParam("csvfile") MultipartFile csvfile) throws ConfigurationException{
			
			// Checking the upload-file's name before processing
			if (csvfile.getOriginalFilename().isEmpty()) {
				System.out.println("No selected file to upload! Please do the checking  fail");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
			}
		
			// checking the upload file's type is CSV or NOT
			
			if(!Service.isCSVFile(csvfile)) { 
				System.out.println("Error: this is not a CSV file! fail"); 
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		       
			}
			  
			 
			try {
				// save file data to database
				
				Service.saveProducts(csvfile.getInputStream());
				System.out.println("Upload File Successfully!");
				return new ResponseEntity<>(HttpStatus.CREATED);
			} catch (Exception e) {
				System.out.println("fail");
				e.printStackTrace();
			}
			return new ResponseEntity<>(HttpStatus.OK);
		
			
		}

}
