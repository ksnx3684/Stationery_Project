package com.stationery.project.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.stationery.project.util.FileManager;
import com.stationery.project.util.Pager;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private FileManager fileManager;

	public List<ProductDTO> list(Pager pager) throws Exception {
		pager.makeRow();
		pager.makeNum(productDAO.total(pager));
		List<ProductDTO> ar = productDAO.list(pager);
		return ar;
	}

	public int add(ProductDTO productDTO, MultipartFile[] files) throws Exception {
		// thumbnail 넣어줌
//		productDTO.setThumbnail(photo.getOriginalFilename());

		int result = productDAO.add(productDTO); // productDTO에 시퀀스 들어가있
		
		productDTO.setThumbnail(files[0].getOriginalFilename());
		for (int i = 0; i < files.length; i++) {
			if (files[i].isEmpty()) { // file이 비어있으면 다시 위로 올라가서 다음꺼 실행
				// files[i].getSize()==0 //file 비어져있으면 저장 안되도록 하기 위함
				continue;
			}
				String fileName = fileManager.save(files[i], "resources/upload/product/");

				ProductFileDTO productFileDTO = new ProductFileDTO();
				// productNum은 add 실행 후 생성됨
				productFileDTO.setProductNum(productDTO.getProductNum());
				productFileDTO.setFileName(fileName);
				productFileDTO.setOriName(files[i].getOriginalFilename());
				productDAO.addFile(productFileDTO);
		}
		return result;
	}

	public ProductDTO detail(ProductDTO productDTO) throws Exception {
		return productDAO.detail(productDTO);
	}

	public int delete(ProductDTO productDTO) throws Exception {
		return productDAO.delete(productDTO);
	}

	public int update(ProductDTO productDTO) throws Exception {
		return productDAO.update(productDTO);
	}
}
