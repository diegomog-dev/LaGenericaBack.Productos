package co.edu.unbosque.lagenericaGr38Eq5.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import co.edu.unbosque.lagenericaGr38Eq5.repository.ProductoRepository;

@Service
public class CSVService {

	private static String csvExtension = "csv";
	@Autowired
	private ProductoRepository prorepo;
	
	String line="";
	
	public void saveProducts(InputStream file) {
		
		BufferedReader br = null;
		
		try {
			br=new BufferedReader(new InputStreamReader(file, "UTF-8"));
			List<Producto> producto = new ArrayList<Producto>();
			
			while((line=br.readLine())!=null) {
				System.out.println("Aqui4");
				String[] data=line.split(";");
				Producto prod= new Producto();
				prod.setCodProducto(Integer.parseInt(data[0]));
				prod.setNomProducto(data[1]);
				prod.setNitProveedor(Integer.parseInt(data[2]));
				prod.setPrecioCompra(Double.parseDouble(data[3]));
				prod.setIvaCompra(Double.parseDouble(data[4]));
				prod.setPrecioVenta(Double.parseDouble(data[5]));
				producto.add(prod);
				prorepo.saveAll(producto);
				System.out.println("Aqui");
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader Error!");
				e.printStackTrace();
			}
		}
	
	}
	
	public static boolean isCSVFile(MultipartFile file) {
		String extension = file.getOriginalFilename().split("\\.")[1];
		
		if(!extension.equals(csvExtension)) {
			return false;
		}
		
		return true;
	}
}
