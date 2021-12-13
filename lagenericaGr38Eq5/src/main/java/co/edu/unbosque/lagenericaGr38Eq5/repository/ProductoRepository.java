package co.edu.unbosque.lagenericaGr38Eq5.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.unbosque.lagenericaGr38Eq5.model.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String>{
	
	//List<Producto> saveAllProductos(List<Producto> array);
	
	List<Producto> findByNomProducto(String nomProducto);

	Optional<Producto> findByCodProducto(int codProducto);

	void deleteByCodProducto(int codProducto);
}
