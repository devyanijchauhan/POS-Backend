package org.pgs.posback.DTO.Category;

import lombok.Data;
import org.pgs.posback.model.ProductModel;

import java.util.Date;
import java.util.List;

@Data
public class CategoryResponseDTO {

    private Long id;

    private String name;

    private List<ProductModel> products;

    private Date createdAt;

    private Date updatedAt;

}
